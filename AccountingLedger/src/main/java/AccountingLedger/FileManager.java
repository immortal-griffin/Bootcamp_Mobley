package AccountingLedger;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Transaction> readTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;

            bufferedReader.readLine();
            while ((row = bufferedReader.readLine()) != null) {
                String[] brokenUpRow = row.split("\\|");
                LocalDate date = LocalDate.parse(brokenUpRow[0]);
                LocalTime time = LocalTime.parse(brokenUpRow[1]);
                String description = brokenUpRow[2];
                String vendor = brokenUpRow[3];
                float amount = Float.parseFloat(brokenUpRow[4]);
                String status = brokenUpRow[5];

                Transaction transaction = new Transaction(date, time, description, vendor, amount, status);

                transactions.add(transaction);
            }
        } catch (IOException ex) {
            System.out.println("Could not read the file. Check the file and try again.");
        } catch (Exception ex) {
            System.out.println("Could not read data from the file. Check the format.");
        }
        return transactions;
    }

    public static boolean writeTransaction(Transaction transaction) {

        String filePath = "src/main/resources/transactions.csv";

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(filePath, true))) {

            String line = String.format(
                    "%s|%s|%s|%s|%.2f|%s",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount(),
                    transaction.getStatus()
            );

            bufferedWriter.newLine();
            bufferedWriter.write(line);

            System.out.println(UIFormatting.GREEN + "New transaction added to ledger!" + UIFormatting.RESET);
            return true;
        } catch (IOException ex) {
            System.out.println(UIFormatting.RED + "Could not write to the file.");
            return false;
        }
    }
}

//todo: