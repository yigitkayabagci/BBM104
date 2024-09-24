import java.util.ArrayList;
import java.util.Locale;

/**
 * This class contains methods for processing and managing voyage data.
 */
public class AllProcessing{

    /**
     * Generates a Z Report containing information about all voyages.
     *
     * @param allVoyageListBus An ArrayList containing all voyages.
     * @param lastchecker      The number of consecutive empty lines at the end of the data.
     * @param contentlenght    The total length of the content.
     * @param Emptylines       The number of empty lines in the content.
     * @param args             Additional arguments (file path).
     */

    public void Zreport(ArrayList<Bus> allVoyageListBus, int lastchecker , int contentlenght, int Emptylines, String[] args){
        // Sorting the voyage list by ID
        for (int i = 0; i < allVoyageListBus.size() - 1; i++) {
            for (int j = 0; j < allVoyageListBus.size() - i - 1; j++) {
                // Swap elements if the current element is greater than the next
                if (allVoyageListBus.get(j).voyageID > allVoyageListBus.get(j + 1).voyageID) {
                    Bus temp = allVoyageListBus.get(j);
                    allVoyageListBus.set(j, allVoyageListBus.get(j + 1));
                    allVoyageListBus.set(j + 1, temp);
                }
            }
        }
        // Write the Z Report to the file
        Outputwrite.writeToFile(args[1], "Z Report:", true, true);
        Outputwrite.writeToFile(args[1], "----------------", true, true);
        // Check if the voyage list is empty
        if (allVoyageListBus.isEmpty()){
            Outputwrite.writeToFile(args[1], "No Voyages Available!", true, true);
            // Check if the last line is empty
            if (contentlenght-1 -Emptylines == lastchecker){
                Outputwrite.writeToFile(args[1], "----------------", true, false);
            } else {
                Outputwrite.writeToFile(args[1], "----------------", true, true);
            }
        }
        // Iterate over all voyages and write their details to the file
        for (int i = 0; i < allVoyageListBus.size(); i++) {
            // Write voyage details to the file
            Outputwrite.writeToFile(args[1], "Voyage " + allVoyageListBus.get(i).getvoyageID(), true, true);
            Outputwrite.writeToFile(args[1], allVoyageListBus.get(i).getStartingPlace() + "-" + allVoyageListBus.get(i).getEndingPlace(), true, true);

            // Check the type of the voyage and write the corresponding bus sitting plan details.
            if (allVoyageListBus.get(i).getVoyageType() == "Standard"){
                for (int k = 0; k < allVoyageListBus.get(i).rows; k++) {
                    // Write the sitting plan to the file
                    // Loop control the seats and write X if the seat is sold, * if it is empty.
                    for (int j = 0; j < 4; j++) {
                        if (j == 1){
                            Outputwrite.writeToFile(args[1], " ", true, false);
                        }
                        if (j == 2) {
                            Outputwrite.writeToFile(args[1], " | ", true, false);
                        }
                        if(j==3){
                            Outputwrite.writeToFile(args[1], " ", true, false);
                        }
                        if (allVoyageListBus.get(i).seatsArray[k][j] == 1) {
                            Outputwrite.writeToFile(args[1], "X", true, false);
                        } else if (allVoyageListBus.get(i).seatsArray[k][j] == 0) {
                            Outputwrite.writeToFile(args[1], "*", true, false);
                        }
                    }
                    Outputwrite.writeToFile(args[1], "", true, true);
                }
                // Write the revenue to the file
                Outputwrite.writeToFile(args[1], "Revenue: " + String.format(Locale.US, "%.2f", allVoyageListBus.get(i).gettotal_revenue()), true, true);

                // Check if the last line is empty to avoid writing an extra line
                if (contentlenght-1 -Emptylines == lastchecker && i == allVoyageListBus.size()-1){
                    Outputwrite.writeToFile(args[1], "----------------", true, false);
                    }
                else {
                    Outputwrite.writeToFile(args[1], "----------------", true, true);
                }
            }
            // Check if the voyage is a premium type and write the corresponding sitting plan details to the file
            if (allVoyageListBus.get(i).getVoyageType() == "Premium"){
                for (int k = 0; k < allVoyageListBus.get(i).rows; k++) {
                    // Write the sitting plan to the file
                    // Loop control the seats and write X if the seat is sold, * if it is empty.
                    for (int j = 0; j < 3; j++) {
                        if (j == 1){
                            Outputwrite.writeToFile(args[1], " | ", true, false);
                        }
                        if(j==2){
                            Outputwrite.writeToFile(args[1], " ", true, false);
                        }
                        if (allVoyageListBus.get(i).seatsArray[k][j] == 1) {
                            Outputwrite.writeToFile(args[1], "X", true, false);
                        } else if (allVoyageListBus.get(i).seatsArray[k][j] == 0) {
                            Outputwrite.writeToFile(args[1], "*", true, false);
                        }
                    }
                    Outputwrite.writeToFile(args[1], "", true, true);
                }
                // Write the revenue to the file
                Outputwrite.writeToFile(args[1], "Revenue: " + String.format(Locale.US, "%.2f", allVoyageListBus.get(i).gettotal_revenue()), true, true);

                // Check if the last line is empty to avoid writing an extra line
                if (contentlenght-1 - Emptylines == lastchecker && i == allVoyageListBus.size()-1){
                    Outputwrite.writeToFile(args[1], "----------------", true, false);
                }
                else {
                    Outputwrite.writeToFile(args[1], "----------------", true, true);
                }
            }

            // Check if the voyage is a minibus type and write the corresponding sitting plan details to the file
            if (allVoyageListBus.get(i).getVoyageType() == "Minibus"){
                for (int k = 0; k < allVoyageListBus.get(i).rows; k++) {
                    // Write the sitting plan to the file
                    // Loop control the seats and write X if the seat is sold, * if it is empty.
                    for (int j = 0; j < 2; j++) {
                        if (j == 1){
                            Outputwrite.writeToFile(args[1], " ", true, false);
                        }
                        if (allVoyageListBus.get(i).seatsArray[k][j] == 1) {
                            Outputwrite.writeToFile(args[1], "X", true, false);
                        } else if (allVoyageListBus.get(i).seatsArray[k][j] == 0) {
                            Outputwrite.writeToFile(args[1], "*", true, false);
                        }
                    }
                    Outputwrite.writeToFile(args[1], "", true, true);
                }
                // Write the revenue to the file
                Outputwrite.writeToFile(args[1], "Revenue: " + String.format(Locale.US, "%.2f", allVoyageListBus.get(i).gettotal_revenue()), true, true);

                // Check if the last line is empty to avoid writing an extra line
                if (contentlenght-1 - Emptylines == lastchecker && i == allVoyageListBus.size()-1){
                    Outputwrite.writeToFile(args[1], "----------------", true, false);
                }
                else {
                    Outputwrite.writeToFile(args[1], "----------------", true, true);

                }
            }
        }
    }
    /**
     * Prints the details of a specific voyage.
     *
     * @param allVoyageListBus An ArrayList containing all voyages.
     * @param Selected_VoyageID The ID of the voyage to be printed.
     * @param args Additional arguments (file path).
     */
    public void printVoyage(ArrayList<Bus> allVoyageListBus, int Selected_VoyageID, String[] args) {
        // Iterate through all bus voyages in the list
        for (int i = 0; i < allVoyageListBus.size(); i++) {
            // Check if the current voyage ID matches the selected voyage ID
            if (allVoyageListBus.get(i).getvoyageID() == Selected_VoyageID) {
                // Write starting and ending place of the voyage to the file
                Outputwrite.writeToFile(args[1], allVoyageListBus.get(i).getStartingPlace() + "-" + allVoyageListBus.get(i).getEndingPlace(), true, true);

                // Check if the voyage type is Standard
                if (allVoyageListBus.get(i).getVoyageType() == "Standard") {
                    // Loop through each row of seats
                    for (int k = 0; k < allVoyageListBus.get(i).rows; k++) {
                        // Loop through each seat in the row
                        for (int j = 0; j < 4; j++) {
                            // Write appropriate character based on seat availability
                            if (j == 1) {
                                Outputwrite.writeToFile(args[1], " ", true, false);
                            }
                            if (j == 2) {
                                Outputwrite.writeToFile(args[1], " | ", true, false);
                            }
                            if (j == 3) {
                                Outputwrite.writeToFile(args[1], " ", true, false);
                            }
                            if (allVoyageListBus.get(i).seatsArray[k][j] == 1) {
                                Outputwrite.writeToFile(args[1], "X", true, false);
                            } else if (allVoyageListBus.get(i).seatsArray[k][j] == 0) {
                                Outputwrite.writeToFile(args[1], "*", true, false);
                            }
                        }
                        // Start a new line after each row of seats
                        Outputwrite.writeToFile(args[1], "", true, true);
                    }
                    // Write the revenue of the voyage to the file
                    Outputwrite.writeToFile(args[1], "Revenue: " + String.format(Locale.US, "%.2f", allVoyageListBus.get(i).gettotal_revenue()), true, true);
                }
                // Check if the voyage type is Premium
                if (allVoyageListBus.get(i).getVoyageType() == "Premium") {
                    // Loop through each row of seats
                    for (int k = 0; k < allVoyageListBus.get(i).rows; k++) {
                        // Loop through each seat in the row
                        for (int j = 0; j < 3; j++) {
                            // Write appropriate character based on seat availability
                            if (j == 1) {
                                Outputwrite.writeToFile(args[1], " | ", true, false);
                            }
                            if (j == 2) {
                                Outputwrite.writeToFile(args[1], " ", true, false);
                            }
                            if (allVoyageListBus.get(i).seatsArray[k][j] == 1) {
                                Outputwrite.writeToFile(args[1], "X", true, false);
                            } else if (allVoyageListBus.get(i).seatsArray[k][j] == 0) {
                                Outputwrite.writeToFile(args[1], "*", true, false);
                            }
                        }
                        // Start a new line after each row of seats
                        Outputwrite.writeToFile(args[1], "", true, true);
                    }
                    // Write the revenue of the voyage to the file
                    Outputwrite.writeToFile(args[1], "Revenue: " + String.format(Locale.US, "%.2f", allVoyageListBus.get(i).gettotal_revenue()), true, true);
                }
                // Check if the voyage type is Minibus
                if (allVoyageListBus.get(i).getVoyageType() == "Minibus") {
                    // Loop through each row of seats
                    for (int k = 0; k < allVoyageListBus.get(i).rows; k++) {
                        // Loop through each seat in the row
                        for (int j = 0; j < 2; j++) {
                            // Write appropriate character based on seat availability
                            if (j == 1) {
                                Outputwrite.writeToFile(args[1], " ", true, false);
                            }
                            if (allVoyageListBus.get(i).seatsArray[k][j] == 1) {
                                Outputwrite.writeToFile(args[1], "X", true, false);
                            } else if (allVoyageListBus.get(i).seatsArray[k][j] == 0) {
                                Outputwrite.writeToFile(args[1], "*", true, false);
                            }
                        }
                        // Start a new line after each row of seats
                        Outputwrite.writeToFile(args[1], "", true, true);
                    }
                    // Write the revenue of the voyage to the file
                    Outputwrite.writeToFile(args[1], "Revenue: " + String.format(Locale.US, "%.2f", allVoyageListBus.get(i).gettotal_revenue()), true, true);
                }
            }
        }
    }

    /**
     * Cancels a voyage with the given selected voyage ID.
     * Adjusts revenue based on seat type and removes the voyage from the list.
     *
     * @param selectedVoyageID The ID of the voyage to cancel.
     * @param allVoyageListBus The list of all voyages.
     * @param args             The arguments passed to the method.
     */
    public void CancelVoyage(int selectedVoyageID, ArrayList<Bus> allVoyageListBus, String[] args) {
        // Iterate through all voyages in the list
        for (int i = 0; i < allVoyageListBus.size(); i++) {
            // Check if the current voyage ID matches the selected one
            if (allVoyageListBus.get(i).voyageID == selectedVoyageID) {
                int counter = 0;
                // Loop through each row of seats
                for (int j = 0; j < allVoyageListBus.get(i).rows; j++) {
                    for (int k = 0; k < allVoyageListBus.get(i).seats; k++) {
                        // Check if the seat is occupied
                        if (allVoyageListBus.get(i).seatsArray[j][k] == 1) {
                            if (allVoyageListBus.get(i).getVoyageType().equals("Premium")) {
                                // If premium seat, calculate revenue deduction
                                PremiumBus premiumBus = (PremiumBus) allVoyageListBus.get(i);
                                premiumBus.total_revenue -= (k == 0) ? (premiumBus.ticketPrice * (premiumBus.getPremiumFee() + 100)) / 100 : premiumBus.ticketPrice;
                            } else if (allVoyageListBus.get(i).getVoyageType().equals("Standard")) {
                                // If standard seat, calculate revenue deduction
                                NormalBus normalBus = (NormalBus) allVoyageListBus.get(i);
                                normalBus.total_revenue -= normalBus.ticketPrice;
                            }
                            counter++;
                        }
                    }
                }
                // Print the details of the cancelled voyage
                printVoyage(allVoyageListBus, selectedVoyageID, args);
                // Remove the cancelled voyage from the list
                allVoyageListBus.remove(i);
                break;
            }
        }
    }



    /**
     * Fills the voyage list with details of a new premium voyage.
     *
     * @param allVoyageListBus The list of all voyages.
     * @param voyageType       The type of the voyage (e.g., "Premium").
     * @param rows             The number of rows in the bus.
     * @param voyageID         The ID of the voyage.
     * @param startingPlace    The starting place of the voyage.
     * @param endingPlace      The ending place of the voyage.
     * @param ticketPrice      The price of a regular seat.
     * @param premiumFee       The premium fee.
     * @param refundCut        The percentage of refund cut.
     * @param parsedprice      The parsed price of the voyage.
     * @param args             The arguments write  output to file.
     */
    public void fillVoyageList(ArrayList<Bus> allVoyageListBus, String voyageType, int rows, int voyageID, String startingPlace, String endingPlace, double ticketPrice, Integer premiumFee, Integer refundCut, String parsedprice, String[] args) {
        if (voyageType.equals("Premium")) {
            // Write details of the initialized premium voyage to the output file
            Outputwrite.writeToFile(args[1], "COMMAND: INIT_VOYAGE\t" + voyageType + "\t" + voyageID + "\t" + startingPlace + "\t" + endingPlace + "\t" + rows + "\t" + parsedprice + "\t" + refundCut + "\t" + premiumFee, true, true);
            String line = ("Voyage " + voyageID + " was initialized as a premium (1+2) voyage from " + startingPlace + " to " + endingPlace + " with " + String.format(Locale.US, "%.2f", ticketPrice) + " TL priced " + (rows * 2) + " " +
                    "regular seats and " + String.format(Locale.US, "%.2f", ((ticketPrice) * (premiumFee + 100)) / 100) + " TL priced " + (rows) + " premium seats."
                    + " Note that refunds will be " + refundCut + "% less than the paid amount.");
            Outputwrite.writeToFile(args[1], line, true, true);

            // Add the premium bus to the voyage list
            allVoyageListBus.add(new PremiumBus(rows, voyageID, startingPlace, endingPlace, ticketPrice, premiumFee, refundCut));
        }
    }

    /**
     * Fills the voyage list with details of a new standard voyage.
     *
     * @param allVoyageListBus The list of all voyages.
     * @param voyageType       The type of the voyage (e.g., "Standard").
     * @param rows             The number of rows in the bus.
     * @param voyageID         The ID of the voyage.
     * @param startingPlace    The starting place of the voyage.
     * @param endingPlace      The ending place of the voyage.
     * @param ticketPrice      The price of a regular seat.
     * @param refundCut        The percentage of refund cut.
     * @param parsedprice      The parsed price of the voyage.
     * @param args             The arguments write  output to file.
     */
    public void fillVoyageList(ArrayList<Bus> allVoyageListBus, String voyageType, int rows, int voyageID,
                               String startingPlace, String endingPlace, double ticketPrice, int refundCut, String parsedprice, String[] args) {
        if (voyageType.equals("Standard")) {
            // Write details of the initialized standard voyage to the output file
            Outputwrite.writeToFile(args[1], "COMMAND: INIT_VOYAGE\t" + voyageType + "\t" + voyageID + "\t" + startingPlace + "\t" + endingPlace + "\t" + rows + "\t" + parsedprice + "\t" + refundCut, true, true);

            String line = ("Voyage " + voyageID + " was initialized as a standard (2+2) voyage from " + startingPlace + " to " + endingPlace + " with " + String.format(Locale.US, "%.2f", ticketPrice) + " TL priced " + (rows * 4) + " regular seats."
                    + " Note that refunds will be " + refundCut + "% less than the paid amount."
            );
            Outputwrite.writeToFile(args[1], line, true, true);

            // Add the standard bus to the voyage list
            allVoyageListBus.add(new NormalBus(rows, voyageID, startingPlace, endingPlace, ticketPrice, refundCut));
        }
    }


    /**
     * Fills the voyage list with details of a new minibus voyage.
     *
     * @param allVoyageListBus The list of all voyages.
     * @param voyageType       The type of the voyage (e.g., "Minibus").
     * @param rows             The number of rows in the bus.
     * @param voyageID         The ID of the voyage.
     * @param startingPlace    The starting place of the voyage.
     * @param endingPlace      The ending place of the voyage.
     * @param ticketPrice      The price of a regular seat.
     * @param parsedprice      The parsed price of the voyage.
     * @param args             The arguments write  output to file.
     */
    public void fillVoyageList(ArrayList<Bus> allVoyageListBus, String voyageType, int rows, int voyageID,
                               String startingPlace, String endingPlace, double ticketPrice, String parsedprice, String[] args) {
        if (voyageType.equals("Minibus")) {
            // Write details of the initialized minibus voyage to the output file
            Outputwrite.writeToFile(args[1], "COMMAND: INIT_VOYAGE\t" + voyageType + "\t" + voyageID + "\t" + startingPlace + "\t" + endingPlace + "\t" + rows + "\t" + parsedprice, true, true);
            String line5 = ("Voyage " + voyageID + " was initialized as a minibus (2) voyage from " + startingPlace + " to " + endingPlace + " with " + String.format(Locale.US, "%.2f", ticketPrice) + " TL priced " + (rows * 2) + " regular seats."
                    + " Note that minibus tickets are not refundable."
            );
            Outputwrite.writeToFile(args[1], line5, true, true);

            // Add the minibus to the voyage list
            allVoyageListBus.add(new Minibus(rows, voyageID, startingPlace, endingPlace, ticketPrice));
        }
    }

}

