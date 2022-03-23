package brickset;

import lombok.NonNull;
import repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An enum representing the chose positions used by {@code numberOfLegoSetsUsingPcsNr} method.
 * I used this, because no further code is allowed in methods, but a single stream, although using only a {@code String}
 * could lead to several errors (i.e. the users enters an invalid value). Using an enum solves this problem.
 *
 * Positions that can be used
 * {@link #OVER}
 * {@link #UNDER}
 */
enum Position {
    /**
     * Under position.
     */
    UNDER,

    /**
     * Over position.
     */
    OVER
}

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    /**
     * Constructor for LegoSetRepository invoking the super classes constructor.
     */
    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Return the number of LEGO sets under/over a certain amount of a piece number.
     * @param underOrOver an Enum {@code Position.UNDER} or {@code Position.OVER} representing the willing to return the number of lego sets under or over a number.
     * @param nrOfPieces number of pieces for selecting.
     * @return the number of LEGO sets under/over the number specified above.
     */
    public long numberOfLegoSetsUsingPcsNr(@NonNull Position underOrOver, int nrOfPieces) {
        return getAll().stream()
                .filter(legoSet -> underOrOver == Position.UNDER ? legoSet.getPieces() < nrOfPieces : legoSet.getPieces() > nrOfPieces)
                .count();
    }

    /**
     * Returns the lego sets which have a certain theme specified using the {@code theme} parameter.
     * @param theme the name of the interested theme.
     * @return a list of {@code LegoSet} containing the ones which have a theme specified above.
     */
    public List<LegoSet> legoSetWithThemeOf(@NonNull String theme) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals(theme))
                .collect(Collectors.toList());
    }

    /**
     * Prints a number, representing the maximum number of used tags in a LegoSet. If it ({@code tags} is null, 0 is used instead of null.
     */
    public void printMaxTagNumber() {
        getAll().stream()
                .mapToInt(legoSet -> legoSet.getTags() != null ? legoSet.getTags().size() : 0)
                .max()
                .ifPresent(System.out::println);
    }

    /**
     * Calculates the volume of a fully made {@code LegoSet}, and returns the one which takes up the most place (the biggest volume).
     * @return a {@code String} representing the name of the {@code LegoSet}.
     */
    public String biggestSizeLego() {
        return getAll().stream()
                .filter(legoSet -> legoSet.getDimensions() != null &&
                        legoSet.getDimensions().getDepth() != null && legoSet.getDimensions().getHeight() != null && legoSet.getDimensions().getWidth() != null)
                .max((o1, o2) -> {
                    double o1Depth = o1.getDimensions().getDepth();
                    double o1Height = o1.getDimensions().getHeight();
                    double o1Width = o1.getDimensions().getWidth();
                    double o1Volume = o1Depth*o1Height*o1Width;

                    double o2Depth = o2.getDimensions().getDepth();
                    double o2Height = o2.getDimensions().getHeight();
                    double o2Width = o2.getDimensions().getWidth();
                    double o2Volume = o2Depth*o2Height*o2Width;

                    return Double.compare(o1Volume, o2Volume);
                })
                .get().getName();
    }

    /**
     * Calculates how many LegoSets are using each of the {@code PackagingType}.
     * The format is: {@code {..., PackagingType=NumberOfLegoSetsUsingThisType, ...}}
     * @return a {@code Map<String, Long>} in which are the number of {@code LegoSet} for each packaging type.
     */
    public Map<String, Long> legoSetCountByPackagingType() {
        return getAll().stream()
                .map(LegoSet::getPackagingType)
                .filter(Objects::nonNull)
                .map(Enum::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static void main(String[] args) {
        // creating and initializing lego set repository
        var repository = new LegoSetRepository();

        // 1st method
        System.out.println("<---------------------------------------------- 1st method ---------------------------------------------->");
        int nrOfPieces = 500;
        System.out.println("The number of lego sets which have less than " + nrOfPieces + " pieces: " + repository.numberOfLegoSetsUsingPcsNr(Position.UNDER, nrOfPieces));


        // 2nd method
        System.out.println("<---------------------------------------------- 2nd method ---------------------------------------------->");
        String theme = "Games";
        List<LegoSet> legoSets = repository.legoSetWithThemeOf(theme);
        System.out.println("Lego sets with a theme of " + theme + ":");
        legoSets.forEach(System.out::println);

        // 3rd method
        System.out.println("<---------------------------------------------- 3rd method ---------------------------------------------->");
        System.out.print("Max number of tags used in LegoSets: ");
        repository.printMaxTagNumber();

        // 4th method
        System.out.println("<---------------------------------------------- 4th method ---------------------------------------------->");
        System.out.println("Max dimension LegoSet name: " + repository.biggestSizeLego());

        // 5th method
        System.out.println("<---------------------------------------------- 5th method ---------------------------------------------->");
        System.out.println("Numbers of LegoSets in each packaging type: ");
        Map<String, Long> groupingByPackageType = repository.legoSetCountByPackagingType();
        System.out.println(groupingByPackageType);
    }
}
