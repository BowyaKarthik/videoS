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

        // Disconnect the connection
        connection.disconnect();
    }
}
