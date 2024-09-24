import java.util.ArrayList;
import java.util.Locale;


public class BookingSystem {
    /**
     * The main entry point of the program. Reads input from a file, processes commands related to voyages
     * and ticket sales, and writes output to another file.
     *
     * @param args An array of command-line arguments. args[0] should contain the path to the input file,
     *             and args[1] should contain the path to the output file.
     */
    public static void main(String[] args) {
        // Read input from the file specified in the command-line arguments
        String[] allcontent = inputreader.readFile(args[0], false, false);

        // Create an ArrayList to store information about all voyages
        ArrayList<Bus> allVoyageListBus = new ArrayList<>();

        // Initialize instances of other necessary classes
        AllProcessing allProcessing = new AllProcessing();
        DataProducer dataProducer = new DataProducer();

        // Flag to track whether the last command was a Z_REPORT
        boolean lastZreport = false;

        // Write an empty line to the output file to start fresh
        Outputwrite.writeToFile(args[1], "", false, false);

        if (allcontent.length == 0) {
            allProcessing.Zreport(allVoyageListBus, 0,allcontent.length,-1, args);
        }
        else {
            // Iterate through each line of input content
            for (int i = 0; i < allcontent.length; i++) {
                // Split the line into an array of tokens
                String[] parsedcontent = allcontent[i].trim().split("\t");
                // Check the type of command and process accordingly
                if (parsedcontent[0].equals("INIT_VOYAGE")) {
                    // Handle INIT_VOYAGE command
                        lastZreport = false;

                        // Handle error for invalid usage of INIT_VOYAGE command for Premium voyage
                    if (parsedcontent[1].equals("Premium") && parsedcontent.length != 9) {
                            String line = ("COMMAND: " + allcontent[i]);
                            Outputwrite.writeToFile(args[1], line, true, true);
                            String line2= ("ERROR: Erroneous usage of \"INIT_VOYAGE\" command!");
                            Outputwrite.writeToFile(args[1], line2, true, true);
                            continue;
                        }
                    // Handle error for invalid usage of INIT_VOYAGE command for Standart voyage
                    if (parsedcontent[1].equals("Standard") && parsedcontent.length != 8) {
                            String line = ("COMMAND: " + allcontent[i]);
                            Outputwrite.writeToFile(args[1], line, true, true);
                            String line2= ("ERROR: Erroneous usage of \"INIT_VOYAGE\" command!");
                            Outputwrite.writeToFile(args[1], line2, true, true);
                            continue;
                        }
                    // Handle error for invalid usage of INIT_VOYAGE command for Minibus voyage
                    if (parsedcontent[1].equals("Minibus") && parsedcontent.length != 7) {
                            String line = ("COMMAND: " + allcontent[i]);
                            Outputwrite.writeToFile(args[1], line, true, true);
                            String line2 = ("ERROR: Erroneous usage of \"INIT_VOYAGE\" command!");
                            Outputwrite.writeToFile(args[1], line2, true, true);
                            continue;
                        }
                        String voyageType = parsedcontent[1];
                        int rows = Integer.parseInt(parsedcontent[5]);
                        int voyageID = Integer.parseInt(parsedcontent[2]);
                        // Handle error for invalid usage of INIT_VOYAGE command for negative voyageID
                        if (voyageID <= 0) {
                            String line = ("COMMAND: " + allcontent[i]);
                            Outputwrite.writeToFile(args[1], line, true, true);
                            String line2 = ("ERROR: " + parsedcontent[2] + " is not a positive integer, ID of a voyage must be a positive integer!");
                            Outputwrite.writeToFile(args[1], line2, true, true);
                            continue;
                        }
                        // Handle error for invalid usage of INIT_VOYAGE command for negative number of seat rows
                        if (rows <= 0) {
                            String line = ("COMMAND: " + allcontent[i]);
                            Outputwrite.writeToFile(args[1], line, true, true);
                            String line2 = ("ERROR: " + parsedcontent[5] + " is not a positive integer, number of seat rows of a voyage must be a positive integer!");
                            Outputwrite.writeToFile(args[1], line2, true, true);
                            continue;
                        }
                        double ticketPrice = Double.parseDouble(parsedcontent[6]);

                        // Handle error for invalid usage of INIT_VOYAGE command for negative ticket price
                        if (ticketPrice < 0) {
                            String line = ("COMMAND: " + allcontent[i]);
                            Outputwrite.writeToFile(args[1], line, true, true);
                            String line2 = ("ERROR: " + parsedcontent[6] + " is not a positive number, price must be a positive number!");
                            Outputwrite.writeToFile(args[1], line2, true, true);
                            continue;
                        }
                        String startingPlace = parsedcontent[3];
                        String endingPlace = parsedcontent[4];

                        int premiumFee;
                        int refundCut;
                        boolean flag5 = false;

                        // Handle error for invalid usage of INIT_VOYAGE command for duplicate voyageID
                        for (Bus voyageListBus : allVoyageListBus) {
                            if (voyageListBus.voyageID == voyageID) {
                                String line = ("COMMAND: " + allcontent[i]);
                                Outputwrite.writeToFile(args[1], line, true, true);
                                String line2 = ("ERROR: There is already a voyage with ID of " + voyageListBus.voyageID + "!");
                                Outputwrite.writeToFile(args[1], line2, true, true);
                                flag5 = true;
                                break;
                            }
                        }
                        // If there is a duplicate voyageID, skip the current command
                        if (flag5) {
                            continue;
                        }

                        if (voyageType.equals("Premium")) {
                            // Handle error for invalid usage of INIT_VOYAGE command for negative refund cut
                            if (Integer.parseInt(parsedcontent[7]) > 100 || Integer.parseInt(parsedcontent[7]) < 0) {
                                Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                                Outputwrite.writeToFile(args[1], "ERROR: " + parsedcontent[7] + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!", true, true);
                                continue;
                            }
                            // Handle error for invalid usage of INIT_VOYAGE command for negative premium fee
                            if (Integer.parseInt(parsedcontent[8]) < 0) {
                                Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                                Outputwrite.writeToFile(args[1], "ERROR: " + parsedcontent[8] + " is not a non-negative integer, premium fee must be a non-negative integer!", true, true);
                                continue;
                            }

                            premiumFee = Integer.parseInt(parsedcontent[8]);
                            refundCut = Integer.parseInt(parsedcontent[7]);
                            // Fill the voyage list with the information provided in the command
                            allProcessing.fillVoyageList(allVoyageListBus, voyageType, rows, voyageID, startingPlace, endingPlace, ticketPrice, premiumFee, refundCut, parsedcontent[6],args);
                        } else if (voyageType.equals("Standard")) {
                            // Handle error for invalid usage of INIT_VOYAGE command for negative refund cut
                            if (Integer.parseInt(parsedcontent[7]) > 100 || Integer.parseInt(parsedcontent[7]) < 0) {
                                Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                                Outputwrite.writeToFile(args[1], "ERROR: " + parsedcontent[7] + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!", true, true);
                                continue;
                            }
                            refundCut = Integer.parseInt(parsedcontent[7]);
                            // Fill the voyage list with the information provided in the command
                            allProcessing.fillVoyageList(allVoyageListBus, voyageType, rows, voyageID, startingPlace, endingPlace, ticketPrice, refundCut, parsedcontent[6] ,args);
                        } else if (voyageType.equals("Minibus")) {
                            // Fill the voyage list with the information provided in the command
                            allProcessing.fillVoyageList(allVoyageListBus, voyageType, rows, voyageID, startingPlace, endingPlace, ticketPrice, parsedcontent[6],args);
                        } else {
                            // Handle error for invalid usage of INIT_VOYAGE command for unknown voyage type
                            Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                            Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"INIT_VOYAGE\" command!", true, true);
                        }
                    } else if (parsedcontent[0].equals("SELL_TICKET")) {
                        // Handle SELL_TICKET command
                        lastZreport = false;
                        // Handle error for invalid usage of SELL_TICKET command
                        if (parsedcontent.length != 3) {
                            Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                            Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"SELL_TICKET\" command!", true, true);
                            continue;
                        }
                        Outputwrite.writeToFile(args[1], "COMMAND: SELL_TICKET\t" + parsedcontent[1] + "\t" + parsedcontent[2], true, true);
                        int voyageID = Integer.parseInt(parsedcontent[1]);
                        int[] soldSeatsInt = dataProducer.soldSeats(parsedcontent[2], args);

                        // Handle error for invalid usage of SELL_TICKET command for negative seat number
                        // Skips the current command if there is an error
                        if (soldSeatsInt.length == 1 && soldSeatsInt[0] == -2395) {
                            continue;
                        }
                        String empstr = "";
                        int counter = 0;
                        boolean flag = false;

                        // Handle error for invalid usage of SELL_TICKET command for negative seat number
                        for (int k = 0; k < soldSeatsInt.length; k++) {
                            if (soldSeatsInt[k] <= 0) {
                                Outputwrite.writeToFile(args[1], "ERROR: " + soldSeatsInt[k] + " is not a positive integer, seat number must be a positive integer!", true, true);
                                flag = true;
                                break;
                            }
                            // Create a string to store the seat numbers to write like "1-2-3" type.
                            empstr += soldSeatsInt[k];
                            counter++;
                            if (k != soldSeatsInt.length - 1) {
                                empstr += "-";
                            }
                        }

                        // Skips the current command if there is an error
                        if (flag) {
                            continue;
                        }

                        // Check if the voyageID is valid
                        for (int j = 0; j < allVoyageListBus.size(); j++) {
                            if (allVoyageListBus.get(j).voyageID == voyageID) {
                                boolean flag3 = false;

                                for (int a = 0; a < soldSeatsInt.length; a++) {
                                    // Handle error for invalid usage of SELL_TICKET command for seat number greater than the total number of seats
                                    if (soldSeatsInt[a] > allVoyageListBus.get(j).getSeats() * allVoyageListBus.get(j).rows) {
                                        Outputwrite.writeToFile(args[1], "ERROR: There is no such a seat!", true, true);
                                        flag3 = true;
                                        break;
                                    }
                                }

                                // Skips the current command if there is an error
                                if (flag3) {
                                    break;
                                }

                                // Handle error for invalid usage of SELL_TICKET command for already sold seat
                                if (allVoyageListBus.get(j).getSeats() * allVoyageListBus.get(j).rows < soldSeatsInt.length) {
                                    Outputwrite.writeToFile(args[1], "ERROR: There is no such a seat!", true, true);
                                    break;
                                }
                                boolean flag1 = false;

                                // Handle error for invalid usage of SELL_TICKET command for already sold seat
                                for (int k = 0; k < soldSeatsInt.length; k++) {
                                    if (allVoyageListBus.get(j).seatsArray[(soldSeatsInt[k] - 1) / allVoyageListBus.get(j).seats][(soldSeatsInt[k] - 1) % allVoyageListBus.get(j).seats] == 1) {
                                        Outputwrite.writeToFile(args[1], "ERROR: One or more seats already sold!", true, true);
                                        flag1 = true;
                                        break;
                                    }
                                }

                                // Skips the current command if there is an error
                                if (flag1) {
                                    break;
                                }
                                if (allVoyageListBus.get(j).filledSeats + counter > allVoyageListBus.get(j).rows * allVoyageListBus.get(j).seats) {
                                    Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"SELL_TICKET\" command!", true, true);
                                    break;
                                }
                                double firstrevenue = allVoyageListBus.get(j).gettotal_revenue();
                                // Calculate the revenue and update the seat array
                                allVoyageListBus.get(j).revenueCalculator(soldSeatsInt);

                                // Write the output to the file
                                String line = ("Seat " + empstr + " of the Voyage " + parsedcontent[1]
                                        + " from " + allVoyageListBus.get(j).startingPlace + " to "
                                        + allVoyageListBus.get(j).endingPlace + " was successfully sold for "
                                        + String.format(Locale.US, "%.2f", (allVoyageListBus.get(j).total_revenue - firstrevenue)) + " TL.");
                                Outputwrite.writeToFile(args[1], line, true, true);
                                break;
                            }
                            else if (j == allVoyageListBus.size() - 1) {
                                // Handle error for invalid usage of SELL_TICKET command for unknown voyageID
                                Outputwrite.writeToFile(args[1], "ERROR: There is no voyage with ID of " + voyageID + "!", true, true);
                            }
                        }
                    }
                    else if (parsedcontent[0].equals("REFUND_TICKET")) {
                        // Handle REFUND_TICKET command
                        lastZreport = false;

                        // Handle error for invalid usage of REFUND_TICKET command
                        if (parsedcontent.length != 3) {
                            Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                            Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"REFUND_TICKET\" command!", true, true);
                            continue;
                        }
                        Outputwrite.writeToFile(args[1], "COMMAND: REFUND_TICKET\t" + parsedcontent[1] + "\t" + parsedcontent[2], true, true);
                        int voyageID = Integer.parseInt(parsedcontent[1]);
                        int[] refundSeatsInt = dataProducer.refundSeats(parsedcontent[2],args);
                        String empstr = "";

                        // Handle error for invalid usage of REFUND_TICKET command for negative seat number
                        // Skips the current command if there is an error
                        if (refundSeatsInt.length == 1 && refundSeatsInt[0] == -2395) {
                            continue;
                        }
                        int counter = 0;
                        boolean flag = false;

                        for (int k = 0; k < refundSeatsInt.length; k++) {
                            if (refundSeatsInt[k] <= 0) {
                                // Handle error for invalid usage of REFUND_TICKET command for negative seat number
                                Outputwrite.writeToFile(args[1], "ERROR: " + refundSeatsInt[k] + " is not a positive integer, seat number must be a positive integer!", true, true);
                                flag = true;
                                break;
                            }
                            // Create a string to store the seat numbers to write like "1-2-3" type.
                            empstr += refundSeatsInt[k];
                            if (k != refundSeatsInt.length - 1) {
                                empstr += "-";
                            }
                        }

                        // Skips the current command if there is an error
                        if (flag) {
                            continue;
                        }

                        // Check if the voyageID is valid
                        for (int j = 0; j < allVoyageListBus.size(); j++) {
                            if (allVoyageListBus.get(j).voyageID == voyageID) {
                                boolean flag4 = false;
                                for (int a = 0; a < refundSeatsInt.length; a++) {
                                    if (refundSeatsInt[a] > allVoyageListBus.get(j).getSeats() * allVoyageListBus.get(j).rows) {
                                        // Handle error for invalid usage of REFUND_TICKET command for seat number greater than the total number of seats
                                        Outputwrite.writeToFile(args[1], "ERROR: There is no such a seat!", true, true);
                                        flag4 = true;
                                        break;
                                    }
                                }
                                // Skips the current command if there is an error
                                if (flag4) {
                                    break;
                                }
                                // Handle error for invalid usage of REFUND_TICKET command for already empty seat
                                if (allVoyageListBus.get(j).getSeats() * allVoyageListBus.get(j).rows < refundSeatsInt.length) {
                                    Outputwrite.writeToFile(args[1], "ERROR: There is no such a seat!", true, true);
                                    break;
                                }
                                boolean flag1 = false;
                                // Handle error for invalid usage of REFUND_TICKET command for already empty seat
                                for (int k = 0; k < refundSeatsInt.length; k++) {
                                    if (allVoyageListBus.get(j).seatsArray[(refundSeatsInt[k] - 1) / allVoyageListBus.get(j).seats][(refundSeatsInt[k] - 1) % allVoyageListBus.get(j).seats] == 0) {
                                        // Handle error for invalid usage of REFUND_TICKET command for already empty seat
                                        Outputwrite.writeToFile(args[1], "ERROR: One or more seats are already empty!", true, true);
                                        flag1 = true;
                                        break;
                                    }
                                }

                                // Skips the current command if there is an error
                                if (flag1) {
                                    break;
                                }

                                // Handle error for invalid usage of REFUND_TICKET command for negative refund number
                                if (allVoyageListBus.get(j).filledSeats - counter < 0) {
                                    Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"REFUND_TICKET\" command!", true, true);
                                    break;
                                }
                                double firstrevenue1 = allVoyageListBus.get(j).gettotal_revenue();
                                allVoyageListBus.get(j).refundCalculator(refundSeatsInt, args);

                                if (allVoyageListBus.get(j).getVoyageType() == "Standard" || allVoyageListBus.get(j).getVoyageType() == "Premium") {
                                    String line3 = ("Seat " + empstr + " of the Voyage " + parsedcontent[1]
                                            + " from " + allVoyageListBus.get(j).startingPlace + " to "
                                            + allVoyageListBus.get(j).endingPlace + " was successfully refunded for " + String.format(Locale.US, "%.2f", (firstrevenue1 - allVoyageListBus.get(j).total_revenue)) + " TL.");
                                    // Write the output to the file
                                    Outputwrite.writeToFile(args[1], line3, true, true);
                                }


                                break;
                            } else if (j == allVoyageListBus.size() - 1) {
                                // Handle error for invalid usage of REFUND_TICKET command for unknown voyageID
                                Outputwrite.writeToFile(args[1], "ERROR: There is no voyage with ID of " + voyageID + "!", true, true);
                            }
                        }
                    } else if (parsedcontent[0].equals("Z_REPORT")) {
                        // Handle Z_REPORT command
                        lastZreport = true;

                        if (parsedcontent.length != 1) {
                            // Handle error for invalid usage of Z_REPORT command
                            Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                            Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"Z_REPORT\" command!", true, true);
                            continue;
                        }
                        Outputwrite.writeToFile(args[1], "COMMAND: Z_REPORT", true, true);
                        int EmptyLines = dataProducer.lastEmptyLine(allcontent, i);

                        // Call Z_REPORT method to write the output to the file
                        allProcessing.Zreport(allVoyageListBus, i, allcontent.length, EmptyLines, args);
                    } else if (parsedcontent[0].equals("PRINT_VOYAGE")) {
                        // Handle PRINT_VOYAGE command
                        lastZreport = false;
                        if (parsedcontent.length != 2) {
                            Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                            Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"PRINT_VOYAGE\" command!", true, true);
                            continue;
                        }
                    try{
                        // Handle error for invalid usage of PRINT_VOYAGE command for negative voyageID and different string types.
                        if (Integer.parseInt(parsedcontent[1]) > 0) {

                            boolean flag10 = false;
                            for (int j = 0; j < allVoyageListBus.size(); j++) {
                                if (allVoyageListBus.get(j).voyageID == Integer.parseInt(parsedcontent[1])) {
                                    break;
                                } else if (j == allVoyageListBus.size() - 1) {
                                    // Handle error for invalid usage of PRINT_VOYAGE command for unknown voyageID
                                    Outputwrite.writeToFile(args[1], "COMMAND: PRINT_VOYAGE\t" + parsedcontent[1], true, true);
                                    Outputwrite.writeToFile(args[1], "ERROR: There is no voyage with ID of " + parsedcontent[1] + "!", true, true);
                                    flag10 = true;
                                }
                            }
                            // Skips the current command if there is an error
                            if (!flag10) {
                                Outputwrite.writeToFile(args[1], "COMMAND: PRINT_VOYAGE\t" + parsedcontent[1], true, true);
                                Outputwrite.writeToFile(args[1], "Voyage " + parsedcontent[1], true, true);
                                allProcessing.printVoyage(allVoyageListBus, Integer.parseInt(parsedcontent[1]), args);
                            }
                        }
                        else {
                            Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                            Outputwrite.writeToFile(args[1], "ERROR: " + parsedcontent[1] + " is not a positive integer, ID of a voyage must be a positive integer!", true, true);
                        }
                    } catch (NumberFormatException e) {
                        // Handle error for invalid usage of PRINT_VOYAGE command for negative voyageID
                        Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                        Outputwrite.writeToFile(args[1], "ERROR: " + parsedcontent[1] + " is not a positive integer, ID of a voyage must be a positive integer!", true, true);
                    }
                    }
                else if (parsedcontent[0].equals("CANCEL_VOYAGE")) {
                    // Set the lastZreport flag to false
                    lastZreport = false;

                    // Check if the command is correctly formatted
                    if (parsedcontent.length != 2) {
                        Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                        Outputwrite.writeToFile(args[1], "ERROR: Erroneous usage of \"CANCEL_VOYAGE\" command!", true, true);
                        continue;
                    }
                    try {
                        // Check if the voyage ID is a positive integer
                        if (Integer.parseInt(parsedcontent[1]) <= 0) {
                            Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                            Outputwrite.writeToFile(args[1], "ERROR: " + parsedcontent[1] + " is not a positive integer, ID of a voyage must be a positive integer!", true, true);
                            continue;
                        }
                        boolean flag11 = false;

                        // Check if the voyage ID exists in the voyage list
                        for (int j = 0; j < allVoyageListBus.size(); j++) {
                            if (allVoyageListBus.get(j).voyageID == Integer.parseInt(parsedcontent[1])) {
                                break;
                            } else if (j == allVoyageListBus.size() - 1) {
                                Outputwrite.writeToFile(args[1], "COMMAND: CANCEL_VOYAGE\t" + parsedcontent[1], true, true);
                                Outputwrite.writeToFile(args[1], "ERROR: There is no voyage with ID of " + parsedcontent[1] + "!", true, true);
                                flag11 = true;
                            }
                        }
                        if (flag11) {
                            continue;
                        }

                        // Write the successful cancellation of the voyage to the output file
                        Outputwrite.writeToFile(args[1], "COMMAND: CANCEL_VOYAGE\t" + parsedcontent[1], true, true);
                        Outputwrite.writeToFile(args[1], "Voyage " + parsedcontent[1] + " was successfully cancelled!", true, true);
                        Outputwrite.writeToFile(args[1], "Voyage details can be found below:", true, true);
                        int selectedVoyageID = Integer.parseInt(parsedcontent[1]);
                        Outputwrite.writeToFile(args[1], "Voyage " + selectedVoyageID, true, true);

                        // Call the CancelVoyage method to cancel the voyage
                        allProcessing.CancelVoyage(selectedVoyageID, allVoyageListBus, args);
                    } catch (NumberFormatException e) {
                        // Handle the error if the voyage ID is not a positive integer
                        Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                        Outputwrite.writeToFile(args[1], "ERROR: " + parsedcontent[1] + " is not a positive integer, ID of a voyage must be a positive integer!", true, true);
                    }
                } else {
                    // Check if the command is not empty
                    if (!parsedcontent[0].isEmpty()) {
                        Outputwrite.writeToFile(args[1], "COMMAND: " + allcontent[i], true, true);
                        Outputwrite.writeToFile(args[1], "ERROR: There is no command namely " + parsedcontent[0].trim() + "!", true, true);
                        lastZreport = false;
                    }
                }

                // Check if it is the last line of the input content
                if (i == allcontent.length - 1) {
                    // If the last command was not a Z_REPORT, generate a Z_REPORT without printing newline.
                    if (!lastZreport) {
                        allProcessing.Zreport(allVoyageListBus, i, allcontent.length, 0, args);
                    }
                }
                }
            }

    }
}

