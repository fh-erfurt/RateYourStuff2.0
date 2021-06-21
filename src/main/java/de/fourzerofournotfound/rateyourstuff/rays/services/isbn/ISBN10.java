package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ISBN10
 * <p>This class represents the old ISBN10 standard</p>
 * @author Robin Beck, Christoph Frischmuth, Mickey Knop, John Klippstein
 *
 * */
public class ISBN10 {

    //region Attributes
    ////////////////////////////////////////////////////////////////////////////////////
    public static final int    ISBN_10_LENGTH = 10;
    private String isbn;
    //endregion

    //region Constructors
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * creates a valid isbn10
     * @param isbn10 isbn10 string, separated by "-"
     */
    public ISBN10(String isbn10) throws InvalidISBNException {
        if (isValid(isbn10)) {
            this.isbn = isbn10;
        } else {
            throw new InvalidISBNException(isbn10 + "is invalid");
        }
    }
    //endregion

    //region Methods
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * Checks whether an ISBN10 is valid or not
     * @param isbn10    ISBN10 as String separated by "-"
     * @return          true, if the ISBN is valid, fals if not
     */
    public static boolean isValid (String isbn10) {
        return (hasValidFormat(isbn10) && hasValidChecksum(isbn10));
    }


    /**
     * Checks if the format of the ISBN is correct, does not check if the checksum is valid
     * @param isbn10    ISBN10 String separated by "-"
     * @return          true, if the format is valid, false if not
     */
    private static boolean hasValidFormat (String isbn10) {
        //RegEx pattern to determine structure of ISBN
        Pattern isbnPattern = Pattern.compile("\\b\\d{1,5}-\\d{2,7}-\\d{2,7}-(\\d|X)\\b");
        Matcher isbnMatcher = isbnPattern.matcher(isbn10);

        //get isbn length without special delimiters
        int isbnLength = isbn10.replace("-", "").length();
        return ((isbnLength == ISBN_10_LENGTH) && isbnMatcher.matches());
    }


    /**
     * checks if the checksum of the given ISBN10 is correct
     * @param isbn10    ISBN10 string separated by "-"
     * @return          true, if the checksum is correct, false if not
     */
    private static boolean hasValidChecksum(String isbn10) {
        //Extract the checksum of the given ISBN
        char placedCheckSum = extractChecksum(isbn10);
        //Calculate the checksum of the given ISBN
        char actualCheckSum = calculateChecksum(isbn10);

        return placedCheckSum == actualCheckSum;
    }

    /**
     * Used to extract the Checksum that is in an ISBN10 String
     * Does not check whether the checksum is correct or not
     * @param isbn10    ISBN10 String separated with "-"
     * @return          The checksum as char
     */
    private static char extractChecksum(String isbn10) {
        return isbn10.charAt(isbn10.length()-1);
    }

    /**
     * This Method calculates the correct checksum for a given isbn10 string
     * @param isbn10    complete isbn10 string (including the current or a false checksum)
     * @return          the correct checksum
     */
    private static char calculateChecksum(String isbn10) {
        String mainISBN = isbn10;
        mainISBN = mainISBN.substring(0, mainISBN.length()-1).replace("-", "");

        int isbnSum = 0;
        char checkSumChar;
        for(int i = 0; i < mainISBN.length(); i++) {
            int currentValue = Integer.parseInt(String.valueOf(mainISBN.charAt(i)));
            isbnSum += (i + 1) * currentValue;
        }

        int result = isbnSum % 11;

        if(result == 10) {
            checkSumChar = 'X';
        } else {
            checkSumChar = (char) (result + '0');
        }
        return  checkSumChar;
    }

    /**
     * <p>Used to print a the isbn as string</p>
     * @return  isbn string
     */
    @Override
    public String toString() {
        return isbn;
    }
    //endregion
}