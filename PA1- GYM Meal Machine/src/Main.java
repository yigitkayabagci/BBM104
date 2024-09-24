public class Main {
    public static void main(String[] args) {
        //Taking products information from txt file.
        String[] content = inputreader.readFile(args[0], false, false); // Reads the file as it is without discarding or trimming anything and stores it in string array namely content.

        //Initialize variables to access values and product names in product.txt
        int firstindextab;
        int secondindextab;
        String name;
        String price;
        String remain;
        double[][] remainarr = new double[content.length][3];
        String[] namearr = new String[content.length];
        int[] prodpricearr = new int[content.length];

        //Parsing the content array which consists all Product file.
        for (int i = 0; i < content.length; i++) {
            firstindextab = content[i].indexOf("\t");
            secondindextab = content[i].indexOf("\t", firstindextab + 1);
            name = content[i].substring(0, firstindextab);
            namearr[i] = name;
            price = content[i].substring(firstindextab + 1, secondindextab);
            prodpricearr[i] = Integer.valueOf(price);
            remain = content[i].substring(secondindextab + 1);
            String[] remainValues = remain.split("\\s");
            for (int j = 0; j < remainValues.length; j++) {
                remainarr[i][j] = Double.parseDouble(remainValues[j]);
        }

        }
        // Start and fill the Gym Food Machine
        GYMMM gymMachine = new GYMMM();
        outputwriter.writeToFile(args[2],"", false,false);
        gymMachine.fill(namearr, prodpricearr, remainarr,args);
        gymMachine.printGMMOutput(args);

        //Taking data from purchase.txt file and put it in content2.
        String[] content2 = inputreader.readFile(args[1], false, false);
        String[][] content3 = new String[content2.length][4];
        // Variables for storing users choices.
        String[] userchoicestype = new String[content2.length];
        int[]   userchoicesnums = new int[content2.length];
        // 2D array for holding moneys.
        int[][] moneyarray = new int[content2.length][];

        // Parse each line of the second file for selected rules of file.
        for (int i = 0; i < content2.length; i++) {
            content3[i] = content2[i].split("\t");
            String[] moneyValues = content3[i][1].split("\\s");
            moneyarray[i] = new int[moneyValues.length];
            for (int j = 0; j < moneyValues.length; j++) {
                moneyarray[i][j] = Integer.valueOf(moneyValues[j]);
            }
            userchoicestype[i] = content3[i][2];
            userchoicesnums[i] = Integer.valueOf(content3[i][3]);

        }
        /*Using the purchase loader class, the machine is updated by evaluating the user's
            requests and the necessary information and products are provided to the user.*/
        Purchaseloader purchaseloader = new Purchaseloader();
        for (int ind = 0; ind < moneyarray.length; ind++){
            purchaseloader.selectPurchase(moneyarray, userchoicestype, userchoicesnums, gymMachine.slots,ind,args,content2);
        }
        gymMachine.printGMMOutput(args);

    }
}