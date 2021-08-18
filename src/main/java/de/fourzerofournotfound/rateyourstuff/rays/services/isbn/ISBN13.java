package de.fourzerofournotfound.rateyourstuff.rays.services.isbn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ISBN13
 * <p>This class represents the ISBN13 and contains methods to validate isbn13 und transform isbn10 to isbn13</p>
 *
 * @author Robin Beck, Christoph Frischmuth, Mickey Knop, John Klippstein
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
public class ISBN13 {
    //region Attributes
    ////////////////////////////////////////////////////////////////////////////////////
    public static final String ISBN_13_PREFIX = "978-";
    public static final int ISBN_13_LENGTH = 13;

    private final String isbn;
    //endregion

    //region Constructors
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * Used to create a new isbn13 from a valid isbn13 string
     *
     * @param isbn13 isbn13 string, separated with "-"
     * @throws InvalidISBNException if the given isbn13 is not valid
     */
    public ISBN13(String isbn13) throws InvalidISBNException {
        if (isValid(isbn13)) {
            this.isbn = isbn13;
        } else {
            throw new InvalidISBNException(isbn13 + "is invalid");
        }
    }
    //endregion

    //region Methods
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * Checks whether an ISBN13 is valid or not
     *
     * @param isbn13 ISBN13 as String separated by "-"
     * @return true, if the ISBN is valid, fals if not
     */
    public static boolean isValid(String isbn13) {
        return (hasValidFormat(isbn13) && hasValidChecksum(isbn13));
    }


    /**
     * Checks if the format of the ISBN is correct, does not check if the checksum is valid
     *
     * @param isbn13 ISBN13 String separated by "-"
     * @return true, if the format is valid, false if not
     */
    public static boolean hasValidFormat(String isbn13) {
        //RegEx pattern to determine structure of ISBN
        Pattern isbnPattern = Pattern.compile("\\b978-\\d{1,5}-\\d{2,7}-\\d{2,7}-\\d\\b");
        Matcher isbnMatcher = isbnPattern.matcher(isbn13);

        //get isbn length without special delimiters
        int isbnLength = isbn13.replace("-", "").length();
        return ((isbnLength == ISBN_13_LENGTH) && isbnMatcher.matches());
    }

    /**
     * checks if the checksum of the given ISBN10 is correct
     *
     * @param isbn13 ISBN13 string separated by "-"
     * @return true, if the checksum is correct, false if not
     */
    private static boolean hasValidChecksum(String isbn13) {
        //Extract the checksum of the given ISBN
        char placedCheckSum = extractChecksum(isbn13);
        //Calculate the checksum of the given ISBN
        char actualCheckSum = calculateChecksum(isbn13);

        return placedCheckSum == actualCheckSum;
    }

    /**
     * Used to extract the Checksum that is in an ISBN10 String
     * Does not check whether the checksum is correct or not
     *
     * @param isbn13 ISBN13 String separated with "-"
     * @return the checksum as char
     */
    private static char extractChecksum(String isbn13) {
        return isbn13.charAt(isbn13.length() - 1);
    }

    /**
     * This method calculates the correct checksum for a given isbn13 string
     *
     * @param isbn complete isbn13 string (including the current or a false checksum)
     * @return the correct checksum as char
     */
    private static char calculateChecksum(String isbn) {
        String mainISBN = isbn;
        mainISBN = mainISBN.substring(0, mainISBN.length() - 1).replace("-", "");

        int isbnSum = 0;
        int checkSum;
        for (int i = 0; i < mainISBN.length(); i++) {
            int currentValue = Integer.parseInt(String.valueOf(mainISBN.charAt(i)));
            isbnSum += ((i + 1) % 2 == 0) ? 3 * currentValue : currentValue;
        }

        checkSum = 10 - (isbnSum % 10);
        if (checkSum == 10) {
            checkSum = 0;
        }
        return (char) (checkSum + '0');
    }

    /**
     * converts an ISBN10 object to an ISBN13 object
     *
     * @param isbn10 valid isbn10 object
     * @return isbn13 object
     * @throws InvalidISBNException if the isbn is not valid
     */
    public static ISBN13 toISBN13(ISBN10 isbn10) throws InvalidISBNException {
        String isbn13;
        String isbn10String = isbn10.toString();

        isbn13 = ISBN_13_PREFIX + isbn10String;
        char isbn13CheckSum = calculateChecksum(isbn13);
        isbn13 = isbn13.substring(0, isbn13.length() - 1) + isbn13CheckSum;
        return new ISBN13(isbn13);
    }

    /**
     * <p>Used to print the isbn as string</p>
     *
     * @return the isbn string
     */
    public String toString() {
        return isbn;
    }
    //endregion
}