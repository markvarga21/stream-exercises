package brickset;

import org.w3c.dom.ls.LSOutput;
import repository.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {


    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Returns the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     * Return the number of LEGO sets under/over a certain amount of piece number.
     *
     * @param underOrOver a non case sensitive String representing the willing to return the number of lego sets under or over a number.
     * @param nrOfPieces number of pieces for selecting
     * @return the number of LEGO sets under/over the numbers specified
     */
    public long numberOfLegoSetsUsingPcsNr(String underOrOver, int nrOfPieces) {
        switch (underOrOver.toLowerCase()) {
            case "under":
                  return getAll().stream()
                        .filter(l -> l.getPieces() < nrOfPieces)
                        .count();
            case "over":
                return getAll().stream()
                        .filter(l -> l.getPieces() > nrOfPieces)
                        .count();
            default:
                System.out.println("Invalid use of underOrOver parameter!");
                return -1;
        }
    }

    /**
     * Returns the lego sets which have a certain theme specified using the {@code theme} parameter.
     * @param theme the name of the interested theme
     * @return a list of LegoSets containing the ones which have a theme specified above
     */
    public List<LegoSet> legoSetWithThemeOf(String theme) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals(theme))
                .collect(Collectors.toList());
    }

    /**
     * Prints the number representing the number of maximum tags in a LegoSet. If it is null, 0 is used.
     */
    public void printMaxTagNumber() {
        getAll().stream()
                .mapToInt(legoSet -> legoSet.getTags() != null ? legoSet.getTags().size() : 0)
                .max()
                .ifPresent(System.out::println);
    }

    /**
     * Calculates the volume of a fully made LegoSet, and returns the one which takes up the most place (aka. the biggest volume)
     * @return a String representing the name of the LegoSet.
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
     * Calculates how many LegoSets are using each of the {@code} PackagingType.
     * The format is: {..., PackagingType=NumberOfLegoSetsUsingThisType, ...}
     * @return a Map in which are the number of LegoSets for each packaging type.
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
        String underOrOver = "under";
        int nrOfPieces = 500;
        System.out.println("The number of lego sets which have less than 500 pieces: " + repository.numberOfLegoSetsUsingPcsNr(underOrOver, nrOfPieces));


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
