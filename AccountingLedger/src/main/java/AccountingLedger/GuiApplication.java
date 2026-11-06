
package AccountingLedger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import java.util.Comparator;


public class GuiApplication extends Application {

    @Override
    public void start(Stage stage) {

        // ==========================================
        // DASHBOARD HEADING
        // ==========================================

        Label title = new Label("POTARA CUSTOMS");
        title.getStyleClass().add("dashboard-title");

        Label subtitle = new Label("TRANSACTION LEDGER");
        subtitle.getStyleClass().add("dashboard-subtitle");

        VBox heading = new VBox(4, title, subtitle);
        // ==========================================
        // LOAD TRANSACTION DATA
        // ==========================================

        ObservableList<Transaction> transactions =
                FXCollections.observableArrayList(
                        FileManager.readTransactions()
                );


        // ==========================================
        // SEARCH FIELD
        // ==========================================

        TextField searchField = new TextField();

        searchField.setPromptText(
                "Search by vendor or description..."
        );

        searchField.setPrefHeight(40);

        searchField.setStyle(
                "-fx-background-color: #202326;" +
                        "-fx-text-fill: #C3C8CD;" +
                        "-fx-prompt-text-fill: #777F86;" +
                        "-fx-border-color: #41464B;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-background-radius: 5px;"
        );


        // ==========================================
        // TRANSACTION TYPE FILTER
        // ==========================================

        ComboBox<String> transactionTypeFilter =
                new ComboBox<>();

        transactionTypeFilter.getItems().addAll(
                "All Transactions",
                "Deposits",
                "Payments"
        );

        transactionTypeFilter.setValue("All Transactions");
        transactionTypeFilter.setPrefHeight(40);


        // ==========================================
        // VENDOR FILTER
        // ==========================================

        ComboBox<String> vendorFilter =
                new ComboBox<>();

        vendorFilter.getItems().add("All Vendors");

        for (Transaction transaction : transactions) {

            String vendor = transaction.getVendor();

            if (vendor != null &&
                    !vendorFilter.getItems().contains(vendor)) {

                vendorFilter.getItems().add(vendor);
            }
        }

        vendorFilter.setValue("All Vendors");
        vendorFilter.setPrefHeight(40);


        // ==========================================
        // DATE RANGE FILTER
        // ==========================================

        ComboBox<String> dateRangeFilter =
                new ComboBox<>();

        dateRangeFilter.getItems().addAll(
                "All Time",
                "Today",
                "Yesterday",
                "This Week",
                "This Month",
                "Last Month",
                "This Year",
                "Last Year",
                "Custom Range"
        );

        dateRangeFilter.setValue("All Time");
        dateRangeFilter.setPrefHeight(40);


        // ==========================================
        // CUSTOM DATE PICKERS
        // ==========================================

        DatePicker startDatePicker = new DatePicker();

        startDatePicker.setPromptText("Start Date");
        startDatePicker.setPrefHeight(40);

        DatePicker endDatePicker = new DatePicker();

        endDatePicker.setPromptText("End Date");
        endDatePicker.setPrefHeight(40);

        // Custom date pickers are only enabled
        // when Custom Range is selected.

        startDatePicker.setDisable(true);
        endDatePicker.setDisable(true);


        // ==========================================
        // SORTING FILTER
        // ==========================================

        ComboBox<String> sortFilter =
                new ComboBox<>();

        sortFilter.getItems().addAll(
                "Newest First",
                "Oldest First",
                "Highest Amount",
                "Lowest Amount"
        );

        sortFilter.setValue("Newest First");
        sortFilter.setPrefHeight(40);

        // ==========================================
        // SUMMARY CARD LABELS
        // ==========================================

        Label depositsAmount = new Label("$0.00");
        Label paymentsAmount = new Label("$0.00");
        Label netAmount = new Label("$0.00");


        // ==========================================
        // FILTERED AND SORTED LISTS
        // ==========================================

        FilteredList<Transaction> filteredTransactions =
                new FilteredList<>(transactions, transaction -> true);

        SortedList<Transaction> sortedTransactions =
                new SortedList<>(filteredTransactions);

        Button addTransactionButton =
                new Button("+ Add Transaction");

        addTransactionButton.getStyleClass().add(
                "add-transaction-button"
        );

        addTransactionButton.setOnAction(event ->
                openTransactionWindow(
                        stage,
                        transactions,
                        filteredTransactions,
                        depositsAmount,
                        paymentsAmount,
                        netAmount
                )
        );

        HBox header = new HBox(10, heading, addTransactionButton);

        header.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(heading, Priority.ALWAYS);

        header.getStyleClass().add("dashboard-header");

        // ==========================================
        // TRANSACTION TABLE
        // ==========================================

        TableView<Transaction> table =
                new TableView<>();

        table.setItems(sortedTransactions);


        // ==========================================
        // TABLE COLUMNS
        // ==========================================

        TableColumn<Transaction, LocalDate> dateColumn =
                new TableColumn<>("Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );


        TableColumn<Transaction, LocalTime> timeColumn =
                new TableColumn<>("Time");

        timeColumn.setCellValueFactory(
                new PropertyValueFactory<>("time")
        );


        TableColumn<Transaction, String> descriptionColumn =
                new TableColumn<>("Description");

        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<>("description")
        );


        TableColumn<Transaction, String> vendorColumn =
                new TableColumn<>("Vendor");

        vendorColumn.setCellValueFactory(
                new PropertyValueFactory<>("vendor")
        );


        TableColumn<Transaction, Float> amountColumn =
                new TableColumn<>("Amount");

        amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );

        // Format transaction amounts as currency.

        amountColumn.setCellFactory(column -> new TableCell<>() {

            @Override
            protected void updateItem(Float amount, boolean empty) {

                super.updateItem(amount, empty);

                if (empty || amount == null) {

                    setText(null);

                } else {

                    setText(String.format("$%,.2f", amount));
                }
            }
        });


        TableColumn<Transaction, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

        // Color deposits and payments differently.

        statusColumn.setCellFactory(column -> new TableCell<>() {

            @Override
            protected void updateItem(String status, boolean empty) {

                super.updateItem(status, empty);

                if (empty || status == null) {

                    setText(null);
                    setStyle("");

                } else {

                    setText(status);

                    if (status.equalsIgnoreCase("Deposit")) {

                        setStyle(
                                "-fx-text-fill: #78B88A;" +
                                        "-fx-font-weight: bold;"
                        );

                    } else if (status.equalsIgnoreCase("Payment")) {

                        setStyle(
                                "-fx-text-fill: #D6AD55;" +
                                        "-fx-font-weight: bold;"
                        );

                    } else {

                        setStyle("-fx-text-fill: #AEB4BA;");
                    }
                }
            }
        });


        // Disable column-header sorting.
        // Sorting is controlled through our dropdown.

        dateColumn.setSortable(false);
        timeColumn.setSortable(false);
        descriptionColumn.setSortable(false);
        vendorColumn.setSortable(false);
        amountColumn.setSortable(false);
        statusColumn.setSortable(false);


        // Add columns to the table.

        table.getColumns().addAll(
                dateColumn,
                timeColumn,
                descriptionColumn,
                vendorColumn,
                amountColumn,
                statusColumn
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );


        // ==========================================
        // FIRST FILTER ROW
        // ==========================================

        HBox primaryFilterRow = new HBox(12);

        primaryFilterRow.getChildren().addAll(
                searchField,
                transactionTypeFilter,
                vendorFilter
        );

        primaryFilterRow.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(searchField, Priority.ALWAYS);


        // ==========================================
        // SECOND FILTER ROW
        // ==========================================

        HBox dateFilterRow = new HBox(12);

        dateFilterRow.getChildren().addAll(
                dateRangeFilter,
                startDatePicker,
                endDatePicker,
                sortFilter
        );

        dateFilterRow.setAlignment(Pos.CENTER_LEFT);


        // ==========================================
        // COMBINE FILTER ROWS
        // ==========================================

        VBox filterSection = new VBox(12);

        filterSection.getChildren().addAll(
                primaryFilterRow,
                dateFilterRow
        );


        // ==========================================
        // SUMMARY CARDS
        // ==========================================

        HBox summaryCards = new HBox(15);

        summaryCards.getChildren().addAll(
                createSummaryCard("TOTAL DEPOSITS", depositsAmount),
                createSummaryCard("TOTAL PAYMENTS", paymentsAmount),
                createSummaryCard("NET TOTAL", netAmount)
        );

        summaryCards.setAlignment(Pos.CENTER);


        // ==========================================
        // SEARCH LISTENER
        // ==========================================

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applyFilters(
                            filteredTransactions,
                            newValue,
                            transactionTypeFilter.getValue(),
                            vendorFilter.getValue(),
                            dateRangeFilter.getValue(),
                            startDatePicker.getValue(),
                            endDatePicker.getValue(),
                            depositsAmount,
                            paymentsAmount,
                            netAmount
                    );
                }
        );


        // ==========================================
        // TRANSACTION TYPE LISTENER
        // ==========================================

        transactionTypeFilter.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applyFilters(
                            filteredTransactions,
                            searchField.getText(),
                            newValue,
                            vendorFilter.getValue(),
                            dateRangeFilter.getValue(),
                            startDatePicker.getValue(),
                            endDatePicker.getValue(),
                            depositsAmount,
                            paymentsAmount,
                            netAmount
                    );
                }
        );


        // ==========================================
        // VENDOR LISTENER
        // ==========================================

        vendorFilter.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applyFilters(
                            filteredTransactions,
                            searchField.getText(),
                            transactionTypeFilter.getValue(),
                            newValue,
                            dateRangeFilter.getValue(),
                            startDatePicker.getValue(),
                            endDatePicker.getValue(),
                            depositsAmount,
                            paymentsAmount,
                            netAmount
                    );
                }
        );


        // ==========================================
        // DATE RANGE LISTENER
        // ==========================================

        dateRangeFilter.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    boolean customRange =
                            "Custom Range".equals(newValue);

                    startDatePicker.setDisable(!customRange);
                    endDatePicker.setDisable(!customRange);

                    applyFilters(
                            filteredTransactions,
                            searchField.getText(),
                            transactionTypeFilter.getValue(),
                            vendorFilter.getValue(),
                            newValue,
                            startDatePicker.getValue(),
                            endDatePicker.getValue(),
                            depositsAmount,
                            paymentsAmount,
                            netAmount
                    );
                }
        );


        // ==========================================
        // START DATE LISTENER
        // ==========================================

        startDatePicker.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applyFilters(
                            filteredTransactions,
                            searchField.getText(),
                            transactionTypeFilter.getValue(),
                            vendorFilter.getValue(),
                            dateRangeFilter.getValue(),
                            newValue,
                            endDatePicker.getValue(),
                            depositsAmount,
                            paymentsAmount,
                            netAmount
                    );
                }
        );


        // ==========================================
        // END DATE LISTENER
        // ==========================================

        endDatePicker.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applyFilters(
                            filteredTransactions,
                            searchField.getText(),
                            transactionTypeFilter.getValue(),
                            vendorFilter.getValue(),
                            dateRangeFilter.getValue(),
                            startDatePicker.getValue(),
                            newValue,
                            depositsAmount,
                            paymentsAmount,
                            netAmount
                    );
                }
        );


        // ==========================================
        // SORTING LISTENER
        // ==========================================

        sortFilter.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    applySorting(
                            sortedTransactions,
                            newValue
                    );
                }
        );


        // ==========================================
        // INITIAL FILTER AND SORT
        // ==========================================

        applyFilters(
                filteredTransactions,
                searchField.getText(),
                transactionTypeFilter.getValue(),
                vendorFilter.getValue(),
                dateRangeFilter.getValue(),
                startDatePicker.getValue(),
                endDatePicker.getValue(),
                depositsAmount,
                paymentsAmount,
                netAmount
        );

        applySorting(
                sortedTransactions,
                sortFilter.getValue()
        );


        // ==========================================
        // MAIN LAYOUT
        // ==========================================

        VBox root = new VBox(
                20,
                header,
                summaryCards,
                filterSection,
                table
        );

        root.setStyle(
                "-fx-background-color: #151719;" +
                        "-fx-padding: 25px;"
        );

        VBox.setVgrow(table, Priority.ALWAYS);
        // ==========================================
        // WINDOW SETUP
        // ==========================================

        Scene scene = new Scene(root, 1200, 750);

        scene.getStylesheets().add(
                getClass().getResource("/obsidian.css").toExternalForm()
        );

        stage.setTitle("Obsidian | Transaction Manager");

        stage.setScene(scene);

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.show();
    }


    // ==============================================
    // APPLY FILTERS
    // ==============================================

    private void openTransactionWindow(
            Stage owner,
            ObservableList<Transaction> transactions,
            FilteredList<Transaction> filteredTransactions,
            Label depositsAmount,
            Label paymentsAmount,
            Label netAmount
    ) {

        Stage modal = new Stage();

        modal.initOwner(owner);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setTitle("Add Transaction");
        modal.setResizable(false);

        // Input fields
        DatePicker dateField = new DatePicker(LocalDate.now());

        TextField timeField = new TextField(
                LocalTime.now().withSecond(0).withNano(0).toString()
        );

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter description");

        TextField vendorField = new TextField();
        vendorField.setPromptText("Enter vendor");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        ComboBox<String> statusField = new ComboBox<>();
        statusField.getItems().addAll("Deposit", "Payment");
        statusField.setValue("Payment");

        Label messageLabel = new Label();

        Button saveButton = new Button("Save Transaction");
        Button cancelButton = new Button("Cancel");

        // Save button action
        saveButton.setOnAction(event -> {

            try {
                LocalDate date = dateField.getValue();

                LocalTime time = LocalTime.parse(timeField.getText());

                String description = descriptionField.getText().trim();
                String vendor = vendorField.getText().trim();

                float amount = Float.parseFloat(amountField.getText());

                String status = statusField.getValue();

                if (date == null ||
                        description.isEmpty() ||
                        vendor.isEmpty() ||
                        amountField.getText().isBlank() ||
                        status == null ||
                        !Float.isFinite(amount)) {

                    messageLabel.setText("Please complete all fields.");
                    return;
                }

                // Normalize transaction amount
                if ("Payment".equals(status)) {
                    amount = -Math.abs(amount);
                } else {
                    amount = Math.abs(amount);
                }

                Transaction transaction = new Transaction(
                        date,
                        time,
                        description,
                        vendor,
                        amount,
                        status
                );

                System.out.println("Transactions loaded: " + transactions.size());

                // Save to CSV
                if (FileManager.writeTransaction(transaction)) {

                    // Update main dashboard
                    transactions.add(transaction);

                    updateSummaryCards(
                            filteredTransactions,
                            depositsAmount,
                            paymentsAmount,
                            netAmount
                    );

                    modal.close();

                } else {

                    messageLabel.setText("Unable to save transaction.");

                }

            } catch (NumberFormatException ex) {

                messageLabel.setText("Please enter a valid amount.");

            } catch (Exception ex) {

                messageLabel.setText("Check your date and time format.");

            }

        });

        cancelButton.setOnAction(event -> modal.close());

        // Layout
        GridPane form = new GridPane();

        form.setHgap(12);
        form.setVgap(15);
        form.setPadding(new Insets(25));

        form.add(new Label("Date"), 0, 0);
        form.add(dateField, 1, 0);

        form.add(new Label("Time"), 0, 1);
        form.add(timeField, 1, 1);

        form.add(new Label("Description"), 0, 2);
        form.add(descriptionField, 1, 2);

        form.add(new Label("Vendor"), 0, 3);
        form.add(vendorField, 1, 3);

        form.add(new Label("Amount"), 0, 4);
        form.add(amountField, 1, 4);

        form.add(new Label("Transaction Type"), 0, 5);
        form.add(statusField, 1, 5);

        HBox buttons = new HBox(10, cancelButton, saveButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(15, form, messageLabel, buttons);
        layout.setPadding(new Insets(10));
        layout.getStyleClass().add("modal-container");

        Scene scene = new Scene(layout);

        scene.getStylesheets().add(
                getClass().getResource("/obsidian.css").toExternalForm()
        );

        modal.setScene(scene);
        modal.showAndWait();
    }

    private void applyFilters(
            FilteredList<Transaction> filteredTransactions,
            String searchText,
            String transactionType,
            String selectedVendor,
            String dateRange,
            LocalDate startDate,
            LocalDate endDate,
            Label depositsAmount,
            Label paymentsAmount,
            Label netAmount) {


        // Calculate date boundaries once per filter update.

        LocalDate today = LocalDate.now();

        LocalDate rangeStart = null;
        LocalDate rangeEnd = null;

        switch (dateRange) {

            case "Today" -> {
                rangeStart = today;
                rangeEnd = today;
            }

            case "Yesterday" -> {
                rangeStart = today.minusDays(1);
                rangeEnd = rangeStart;
            }

            case "This Week" -> {
                rangeStart = today.with(
                        TemporalAdjusters.previousOrSame(
                                DayOfWeek.MONDAY
                        )
                );

                rangeEnd = rangeStart.plusDays(6);
            }

            case "This Month" -> {
                rangeStart = today.withDayOfMonth(1);

                rangeEnd = today.withDayOfMonth(
                        today.lengthOfMonth()
                );
            }

            case "Last Month" -> {
                LocalDate lastMonth = today.minusMonths(1);

                rangeStart = lastMonth.withDayOfMonth(1);

                rangeEnd = lastMonth.withDayOfMonth(
                        lastMonth.lengthOfMonth()
                );
            }

            case "This Year" -> {
                rangeStart = today.withDayOfYear(1);

                rangeEnd = today.withDayOfYear(
                        today.lengthOfYear()
                );
            }

            case "Last Year" -> {
                LocalDate lastYear = today.minusYears(1);

                rangeStart = lastYear.withDayOfYear(1);

                rangeEnd = lastYear.withDayOfYear(
                        lastYear.lengthOfYear()
                );
            }

            case "Custom Range" -> {
                rangeStart = startDate;
                rangeEnd = endDate;
            }
        }


        // Determine whether the custom range is valid.

        boolean invalidCustomRange =
                "Custom Range".equals(dateRange) &&
                        startDate != null &&
                        endDate != null &&
                        startDate.isAfter(endDate);

        final LocalDate filterStart = rangeStart;
        final LocalDate filterEnd = rangeEnd;

        // Apply all filtering conditions.

        filteredTransactions.setPredicate(transaction -> {


            // SEARCH FILTER

            boolean matchesSearch = true;

            if (searchText != null && !searchText.isBlank()) {

                String search = searchText.toLowerCase();

                String vendor = transaction.getVendor() == null
                        ? ""
                        : transaction.getVendor().toLowerCase();

                String description =
                        transaction.getDescription() == null
                                ? ""
                                : transaction.getDescription().toLowerCase();

                matchesSearch =
                        vendor.contains(search) ||
                                description.contains(search);
            }


            // TRANSACTION TYPE FILTER

            boolean matchesType = switch (transactionType) {

                case "Deposits" ->
                        "Deposit".equalsIgnoreCase(
                                transaction.getStatus()
                        );

                case "Payments" ->
                        "Payment".equalsIgnoreCase(
                                transaction.getStatus()
                        );

                default -> true;
            };


            // VENDOR FILTER

            boolean matchesVendor =
                    selectedVendor == null ||
                            selectedVendor.equals("All Vendors") ||
                            selectedVendor.equalsIgnoreCase(
                                    transaction.getVendor()
                            );


            // DATE FILTER

            boolean matchesDate = true;

            LocalDate transactionDate = transaction.getDate();

            if (invalidCustomRange) {

                matchesDate = false;

            } else if (filterStart != null && filterEnd != null) {

                matchesDate =
                        transactionDate != null &&
                                !transactionDate.isBefore(filterStart) &&
                                !transactionDate.isAfter(filterEnd);
            }


            // COMBINE ALL FILTERS

            return matchesSearch &&
                    matchesType &&
                    matchesVendor &&
                    matchesDate;
        });


        // UPDATE SUMMARY CARDS

        updateSummaryCards(
                filteredTransactions,
                depositsAmount,
                paymentsAmount,
                netAmount
        );
    }


    // ==============================================
    // APPLY SORTING
    // ==============================================

    private void applySorting(
            SortedList<Transaction> sortedTransactions,
            String sortOption) {


        Comparator<Transaction> comparator;

        switch (sortOption) {

            case "Oldest First" ->

                    comparator =
                            Comparator.comparing(Transaction::getDate)
                                    .thenComparing(Transaction::getTime);


            case "Highest Amount" ->

                    comparator =
                            Comparator.comparingDouble(
                                    Transaction::getAmount
                            ).reversed();


            case "Lowest Amount" ->

                    comparator =
                            Comparator.comparingDouble(
                                    Transaction::getAmount
                            );


            case "Newest First" ->

                    comparator =
                            Comparator.comparing(Transaction::getDate)
                                    .thenComparing(Transaction::getTime)
                                    .reversed();


            default ->

                    comparator =
                            Comparator.comparing(Transaction::getDate)
                                    .thenComparing(Transaction::getTime)
                                    .reversed();
        }


        sortedTransactions.setComparator(comparator);
    }


    // ==============================================
    // UPDATE SUMMARY CARDS
    // ==============================================

    private void updateSummaryCards(
            Iterable<Transaction> transactions,
            Label depositsAmount,
            Label paymentsAmount,
            Label netAmount) {

        double totalDeposits = 0;

        double totalPayments = 0;


        for (Transaction transaction : transactions) {

            if ("Deposit".equalsIgnoreCase(
                    transaction.getStatus())) {

                totalDeposits += transaction.getAmount();

            } else if ("Payment".equalsIgnoreCase(
                    transaction.getStatus())) {

                totalPayments += transaction.getAmount();
            }
        }


        double netTotal = totalDeposits + totalPayments;


        depositsAmount.setText(
                String.format("$%,.2f", totalDeposits)
        );

        paymentsAmount.setText(
                String.format("$%,.2f", totalPayments)
        );

        netAmount.setText(
                String.format("$%,.2f", netTotal)
        );
    }


    // ==============================================
    // CREATE SUMMARY CARD
    // ==============================================

    private VBox createSummaryCard(
            String heading,
            Label amountLabel) {

        Label headingLabel = new Label(heading);

        headingLabel.setStyle(
                "-fx-text-fill: #AEB4BA;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;"
        );


        amountLabel.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );


        VBox card = new VBox(12);

        card.getChildren().addAll(
                headingLabel,
                amountLabel
        );

        card.setAlignment(Pos.CENTER_LEFT);

        card.setStyle(
                "-fx-background-color: #202326;" +
                        "-fx-border-color: #41464B;" +
                        "-fx-border-radius: 6px;" +
                        "-fx-background-radius: 6px;" +
                        "-fx-padding: 20px;"
        );

        HBox.setHgrow(card, Priority.ALWAYS);

        return card;
    }


    // ==============================================
    // MAIN METHOD
    // ==============================================

    public static void main(String[] args) {

        launch(args);
    }
}
