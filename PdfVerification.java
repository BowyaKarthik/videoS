package org.testleaf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.pdfbox.io.IOUtils;

public class PdfVerification {

    public static void main(String[] args) {
        // URL of the PDF file
        String pdfUrl = "https://file-examples.com/storage/fe44eeb9cb66ab8ce934f14/2017/10/file-sample_150kB.pdf";

        try {
            // Read and print the PDF content from the URL
            readAndPrintPdfContentFromUrl(pdfUrl);
        } catch (IOException e) {
        }
    }

    public static void readAndPrintPdfContentFromUrl(String pdfUrl) throws IOException {
        // Open a connection to the URL
        URL url = new URL(pdfUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Check the response code
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Get the input stream from the connection
            try (InputStream inputStream = connection.getInputStream()){
            byte[] pdfBytes = IOUtils.toByteArray(inputStream);
                 PDDocument document = Loader.loadPDF(pdfBytes);

                // Create PDFTextStripper to extract text
                PDFTextStripper pdfStripper = new PDFTextStripper();

                // Extract text from the PDF
                String pdfText = pdfStripper.getText(document);

                // Print the extracted text
                System.out.println("PDF Content:");
                System.out.println(pdfText);
            }
        } else {
            System.out.println("Failed to fetch the PDF. HTTP response code: " + responseCode);
        }


         <repository>
        <id>local-repo</id>
        <url>file://${user.home}/.m2/repository</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>

        // Disconnect the connection
        connection.disconnect();
    }
}


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
 
public class Main {
    public static void main(String[] args) {
        // Define MinAge and MaxAge
        int MinAge = 20;
        int MaxAge = 40;
 
        // Get a random date between MinAge to MaxAge years in the past
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date currentDate = new Date();
        Random random = new Random();
        int randomYears = MinAge + random.nextInt(MaxAge - MinAge + 1);
        currentDate.setYear(currentDate.getYear() - randomYears);
        String randomDob = dateFormat.format(currentDate);
 
        // Get a random number between '1000' and '9999'
        int randomAgeSeed = 1000 + random.nextInt(9000);
 
        // Form random_id_no_check_digit
        String randomIdNoCheckDigit = randomDob + randomAgeSeed + "08";
 
        // Get Check Digit
        int sum = 0;
        int sum_len = 0;
        String idWithoutCheckDigit = randomIdNoCheckDigit.substring(0, randomIdNoCheckDigit.length() - 1);
        for (int i = 0; i < idWithoutCheckDigit.length(); i++) {
            char digit = idWithoutCheckDigit.charAt(i);
            int num = Character.getNumericValue(digit);
            sum += num;
            sum_len += 1;
        }
        int checkDigit = (sum % 11);
        String idCustomer = randomIdNoCheckDigit + checkDigit;
 
        // Determine gender_customer
        String genderCustomer = "female";
        if (randomAgeSeed > 4999) {
            genderCustomer = "male";
        }
 
        // Convert date time to custom format
        SimpleDateFormat dobFormat = new SimpleDateFormat("dd MMM yyyy");
        Date dobDate = new Date();
        dobDate.setYear(Integer.parseInt(randomDob.substring(0, 2)) + 1900);
        dobDate.setMonth(Integer.parseInt(randomDob.substring(2, 4)) - 1);
        dobDate.setDate(Integer.parseInt(randomDob.substring(4)));
        String dobCustomer = dobFormat.format(dobDate);
 
        // Print results
        System.out.println("ID : " + idCustomer);
        System.out.println("Gender : " + genderCustomer);
    }
}
