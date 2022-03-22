package brickset;

import repository.Repository;

import java.util.List;
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



    }
}
