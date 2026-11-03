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
                LocalDate date = LocalDate.parse((brokenUpRow[0]));
                LocalTime time = LocalTime.parse(brokenUpRow[1]);
                String description = brokenUpRow[2];
                String vendor = brokenUpRow[3];
                float amount = Float.parseFloat(brokenUpRow[4]);
                String status = brokenUpRow[5];

                Transaction transaction = new Transaction(date, time, description, vendor, amount, status);

                transactions.add(transaction);
            }
        } catch (IOException ex) {
            System.out.println("Could not read the file. Check the file and try again. ");
        } catch (Exception ex) {
            System.out.println("Could not read data from the file. Check the format.");
        }
        return transactions;
    }

    public static void writeTransaction(Transaction transaction) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = String.format("%s|%s|%s|%s|%.2f|%s",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount(),
                    transaction.getStatus());

            bufferedWriter.newLine();
            bufferedWriter.write(line);

            bufferedWriter.close();

            System.out.println("New transaction added to ledger!");
        } catch (IOException ex) {
            System.out.println("Could not write to the file.");
        } catch (Exception ex) {
            System.out.println("Error when handling the data.");
        }
    }
}

//todo: