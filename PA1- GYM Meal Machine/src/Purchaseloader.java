public class Purchaseloader {
    /**
     * Checks the validity of money types and return the total amount of money.
     * @param moneyarray The array containing the money denominations.
     * @param index The index of the money array to check.
     * @param args File-related arguments for output.
     * @return The total sum of valid money types.
     */
    public int moneychecker(int moneyarray[][], int index, String[] args) {
        int summoney = 0;
        for (int i = 0; i < moneyarray[index].length; i++) {
            if (moneyarray[index][i] == 1 || moneyarray[index][i] == 5 || moneyarray[index][i] == 10 || moneyarray[index][i] == 20
                    || moneyarray[index][i] == 50 || moneyarray[index][i] == 100  || moneyarray[index][i] == 200){
                summoney = moneyarray[index][i] + summoney;
            }
            else {
                String line = "INFO: Invalid money type.";
                outputwriter.writeToFile(args[2], line, true, true);
            }
        }
        return summoney;
    }

    /**
     * Formats the money denominations into a string for output.
     * @param moneyarray The array containing the money denominations.
     * @param index The index of the money array to format.
     * @return A string representing the money denominations.
     */
    public String moneyprinter(int moneyarray[][], int index) {
        String empstr = ""; // Initialize an empty string

        // Iterate over the elements of moneyarray[index] and concatenate them to empstr
        for (int i = 0; i < moneyarray[index].length; i++) {
            empstr += moneyarray[index][i] + " "; // Concatenate each element followed by a space
        }

        // Return the trimmed string to remove the trailing space
        return empstr.trim();
    }

    /**
     * Selects a purchase based on user choice and available products.
     * @param moneyarray The array containing the money denominations.
     * @param userchoicestype The type of purchase chosen by the user.
     * @param userchoicesnums The quantity or value of the purchase chosen by the user.
     * @param products The array of products available for purchase.
     * @param ind The index of the user choice.
     * @param args File-related arguments for output.
     * @return 0 if successful, -1 if unsuccessful.
     */
    public int selectPurchase(int moneyarray[][], String userchoicestype[], int userchoicesnums[], Product[][] products, int ind, String[] args,String content[]) {
        boolean flag = false;
        String choice = userchoicestype[ind];
        switch (choice) {
            case "CARB":
                // Handle carbohydrate-based purchases
                String line = "INPUT: " + content[ind] ;
                outputwriter.writeToFile(args[2], line, true, true);
                int money = (moneychecker(moneyarray, ind, args));
                // Iterate over products to find a suitable match
                for (int i = 0; i < products.length; i++) {
                    if (flag) {
                        break;
                    }
                    /*It is checked whether the desired value of the product is between -5 +5,
                        more than 0 and the price is sufficient.*/
                    for (int j = 0; j < products[i].length; j++) {
                        if (products[i][j] != null && userchoicesnums[ind] - 5 <= products[i][j].getCarbohydrate()
                                && products[i][j].getCarbohydrate() <= userchoicesnums[ind] + 5 && products[i][j].getCount() > 0) {
                            if (money >= products[i][j].getPrice()) {
                                products[i][j].setCount(products[i][j].getCount() - 1);
                                String line2 = "PURCHASE: You have bought one " + products[i][j].getName();
                                outputwriter.writeToFile(args[2], line2, true, true);
                                String line3 = "RETURN: Returning your change: " +
                                        (money - (int) products[i][j].getPrice()) + " TL";
                                outputwriter.writeToFile(args[2], line3, true, true);
                                flag = true;
                                break;
                                // If money is not enough for buying print insufficient money.
                            } else {
                                // If money is not enough for buying print insufficient money.
                                String line4 = "INFO: Insufficient money, try again with more money.";
                                outputwriter.writeToFile(args[2], line4, true, true);
                                String line5 = "RETURN: Returning your change: " + money + " TL";
                                outputwriter.writeToFile(args[2], line5, true, true);
                                return -1;
                            }
                        }
                    }
                }
                // The machine informs if the product is not available
                if (!flag) {
                    String line6 = "INFO: Product not found, your money will be returned.";
                    outputwriter.writeToFile(args[2], line6, true, true);
                    String line7 = "RETURN: Returning your change: " + money + " TL";
                    outputwriter.writeToFile(args[2], line7, true, true);
                }
                return -1;
            case "PROTEIN":
                // Handle protein-based purchases
                String line8 = "INPUT: " + content[ind] ;
                outputwriter.writeToFile(args[2], line8, true, true);
                int money1 = (moneychecker(moneyarray, ind, args));
                // Iterate over products to find a suitable match
                for (int i = 0; i < products.length; i++) {
                    if (flag) {
                        break;
                    }
                    /*It is checked whether the desired value of the product is between -5 +5,
                        more than 0 and the price is sufficient.*/
                    for (int j = 0; j < products[i].length; j++) {
                        if (products[i][j] != null && userchoicesnums[ind] - 5 <= products[i][j].getProtein()
                                && products[i][j].getProtein() <= userchoicesnums[ind] + 5 && products[i][j].getCount() > 0) {
                            if (money1 >= products[i][j].getPrice()) {
                                products[i][j].setCount(products[i][j].getCount() - 1);
                                String line2 = "PURCHASE: You have bought one " + products[i][j].getName();
                                outputwriter.writeToFile(args[2], line2, true, true);
                                String line3 = "RETURN: Returning your change: " +
                                        (money1 - (int) products[i][j].getPrice()) + " TL";
                                outputwriter.writeToFile(args[2], line3, true, true);
                                flag = true;
                                break;
                            } else {
                                // If money is not enough for buying print insufficient money.
                                String line4 = "INFO: Insufficient money, try again with more money.";
                                outputwriter.writeToFile(args[2], line4, true, true);
                                String line5 = "RETURN: Returning your change: " + money1 + " TL";
                                outputwriter.writeToFile(args[2], line5, true, true);
                                return -1;
                            }
                        }
                    }
                }
                // The machine informs if the product is not available
                if (!flag) {
                    String line6 = "INFO: Product not found, your money will be returned.";
                    outputwriter.writeToFile(args[2], line6, true, true);
                    String line7 = "RETURN: Returning your change: " + money1 + " TL";
                    outputwriter.writeToFile(args[2], line7, true, true);
                }
                return -1;
            case "FAT":
                // Handle fat-based purchases
                String line9 = "INPUT: " + content[ind] ;
                outputwriter.writeToFile(args[2], line9, true, true);
                int money2 = (moneychecker(moneyarray, ind, args));
                // Iterate over products to find a suitable match
                for (int i = 0; i < products.length; i++) {
                    if (flag) {
                        break;
                    }
                    /*It is checked whether the desired value of the product is between -5 +5,
                        more than 0 and the price is sufficient.*/
                    for (int j = 0; j < products[i].length; j++) {
                        if (products[i][j] != null && userchoicesnums[ind] - 5 <= products[i][j].getFat()
                                && products[i][j].getFat() <= userchoicesnums[ind] + 5 && products[i][j].getCount() > 0) {
                            if (money2 >= products[i][j].getPrice()) {
                                products[i][j].setCount(products[i][j].getCount() - 1);
                                String line2 = "PURCHASE: You have bought one " + products[i][j].getName();
                                outputwriter.writeToFile(args[2], line2, true, true);
                                String line3 = "RETURN: Returning your change: " +
                                        (money2 - (int) products[i][j].getPrice()) + " TL";
                                outputwriter.writeToFile(args[2], line3, true, true);
                                flag = true;
                                break;
                            } else {
                                // If money is not enough for buying print insufficient money.
                                String line4 = "INFO: Insufficient money, try again with more money.";
                                outputwriter.writeToFile(args[2], line4, true, true);
                                String line5 = "RETURN: Returning your change: " + money2 + " TL";
                                outputwriter.writeToFile(args[2], line5, true, true);
                                return -1;
                            }
                        }
                    }
                }
                // The machine informs if the product is not available
                if (!flag) {
                    String line6 = "INFO: Product not found, your money will be returned.";
                    outputwriter.writeToFile(args[2], line6, true, true);
                    String line7 = "RETURN: Returning your change: " + money2 + " TL";
                    outputwriter.writeToFile(args[2], line7, true, true);
                }
                return -1;
            case "NUMBER":
                // Handle number-based purchases
                String line10 = "INPUT: " + content[ind] ;
                outputwriter.writeToFile(args[2], line10, true, true);
                int money3 = (moneychecker(moneyarray, ind, args));
                if (userchoicesnums[ind] <= 24) {
                    int a = (userchoicesnums[ind]) / 4;
                    int b = (userchoicesnums[ind]) % 4;
                    /*It is checked whether the desired number of the slot is suitable or not, also checked
                        more than 0 and the price is sufficient.*/
                    if (products[a][b] != null && products[a][b].getCount() > 0) {
                        if (money3 >= products[a][b].getPrice()) {
                            products[a][b].setCount(products[a][b].getCount() - 1);
                            String line2 = "PURCHASE: You have bought one " + products[a][b].getName();
                            outputwriter.writeToFile(args[2], line2, true, true);
                            String line3 = "RETURN: Returning your change: " +
                                    (money3 - (int) products[a][b].getPrice()) + " TL";
                            outputwriter.writeToFile(args[2], line3, true, true);
                        } else {
                            // If money is not enough for buying print insufficient money.
                            String line4 = "INFO: Insufficient money, try again with more money.";
                            outputwriter.writeToFile(args[2], line4, true, true);
                            String line5 = "RETURN: Returning your change: " + money3 + " TL";
                            outputwriter.writeToFile(args[2], line5, true, true);
                            return -1;
                        }
                    } else {
                        //If the selected slot is empty, the machine prints the slot empty information.
                        String line6 = "INFO: This slot is empty, your money will be returned.";
                        outputwriter.writeToFile(args[2], line6, true, true);
                        String line7 = "RETURN: Returning your change: " + money3 + " TL";
                        outputwriter.writeToFile(args[2], line7, true, true);
                        return -1;
                    }
                } else {
                    String line12 = "INFO: Number cannot be accepted. Please try again with another number.";
                    outputwriter.writeToFile(args[2], line12, true, true);
                    String line13 = "RETURN: Returning your change: " + money3 + " TL";
                    outputwriter.writeToFile(args[2], line13, true, true);
                    return -1;
                }
                break;
            case "CALORIE":
                String line11 = "INPUT: " + content[ind] ;
                outputwriter.writeToFile(args[2],line11,true,true);
                int money4 = (moneychecker(moneyarray, ind,args));
                for (int i = 0; i < products.length; i++) {
                    if (flag) {
                        break;
                    }
                    /*It is checked whether the desired value of the product is between -5 +5,
                        more than 0 and the price is sufficient.*/
                    for (int j = 0; j < products[i].length; j++) {
                        if (products[i][j] != null && userchoicesnums[ind] - 5 <= products[i][j].getCalorie()
                                && products[i][j].getCalorie() <= userchoicesnums[ind] + 5 && products[i][j].getCount() > 0) {
                            if (money4 >= products[i][j].getPrice()) {
                                products[i][j].setCount(products[i][j].getCount() - 1);
                                String line2 = ("PURCHASE: You have bought one " + products[i][j].getName());
                                outputwriter.writeToFile(args[2],line2,true,true);
                                String line3 = ("RETURN: Returning your change: "+
                                        (money4 - (int) products[i][j].getPrice()) + " TL");
                                outputwriter.writeToFile(args[2],line3,true,true);
                                flag = true;
                                break;
                            } else {
                                // If money is not enough for buying print insufficient money.
                                String line4 = ("INFO: Insufficient money, try again with more money.");
                                outputwriter.writeToFile(args[2],line4,true,true);
                                String line5 = ("RETURN: Returning your change: "+ money4+" TL");
                                outputwriter.writeToFile(args[2],line5,true,true);
                                return -1;
                            }
                        }
                    }

                    // The machine informs if the product is not available
                }if (!flag) {
                String line14= ("INFO: Product not found, your money will be returned.");
                outputwriter.writeToFile(args[2],line14,true,true);
                String line16 = ("RETURN: Returning your change: " + money4 + " TL");
                outputwriter.writeToFile(args[2],line16,true,true);}
                return -1;
        }

        return 0;
    }

}