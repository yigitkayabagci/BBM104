public class Builder {
public static void main(String[] args) {
    int j;
    String[] content = Reader.readFile(args[0], false, false); // Reads the file as it is without discarding or trimming anything and stores it in string array namely content.
    String[] content2 = Reader.readFile(args[1], false, false); // Reads the file as it is without discarding or trimming anything and stores it in string array namely content.

    for (j = 0; j < content.length; j++) {
        String trycontent[] = content[j].split("\t");
        if (trycontent[0].equals("DECORATION")) {
            break;
        }
    }
    Decoration[] decoration = new Decoration[content.length - j];
    int len = j;
    Classroom[] classrooms = new Classroom[len];
    Inputfiller(j, content, content2, decoration, classrooms, len);
    StringBuilder out = build(classrooms, decoration);
    Outputwriter.writeToFile(args[2],"",false,false);
    Outputwriter.writeToFile(args[2], out.toString(), true,false);
}

public static StringBuilder build(Classroom[] classrooms, Decoration[] decoration ){
    StringBuilder out = new StringBuilder();
    int sum = 0;
    for (Classroom classroom : classrooms) {
        double a = 0;
        double b = 0;
        int floortile_num = 0;
        int wallnum = 0;
        classroom.calculateWall1();
        classroom.calculateWall2();
        for (Decoration dec : decoration) {
                if (dec.getDecName().equals(classroom.getWallDecoration())) {
                    if (classroom.getShape().equals("Circle")){
                        double cost = (((double) classroom.getWall2() / dec.getPermeter())) * dec.getPrice();
                        wallnum = (int)Math.ceil(classroom.getWall1() / dec.getPermeter());
                        a = cost;
                    }
                    else if (classroom.getShape().equals("Rectangle")){
                        int cost1 = (int) Math.ceil(((classroom.getWall1() + classroom.getWall2()) * 2) / dec.getPermeter()) * dec.getPrice();
                        wallnum = (int) Math.ceil(((classroom.getWall1() + classroom.getWall2()) * 2) / dec.getPermeter());
                        a = cost1;
                    }
                    break;
                }
            }
        for (Decoration dec : decoration) {
                if (dec.getDecName().equals(classroom.getFloorDecoration())) {
                    //classroom.calculateFloorArea();
                    if (classroom.getShape().equals("Circle")) {
                        double floor_area = ((double) classroom.getWidth() / 2) * Math.PI * ((double) classroom.getWidth() / 2);
                        floortile_num = (int) Math.ceil(floor_area / dec.getPermeter());
                        int cost = (int) Math.ceil((floor_area / dec.getPermeter())) * dec.getPrice();
                        b = cost;
                    }
                    else if (classroom.getShape().equals("Rectangle")){
                        double floor_area = (classroom.getHeight() * classroom.getWidth());
                        floortile_num = (int) Math.ceil(floor_area / dec.getPermeter());
                        int cost = (int) Math.ceil(((double) floor_area / dec.getPermeter())) * dec.getPrice();
                        b = cost;
                    }

                    break;
                }

        }
        //System.out.println(classroom.getWallDecoration());
        if (classroom.getWalltype().equals("Tile")){
            out.append("Classroom "+ classroom.getName() + " used " + wallnum + " Tiles for walls and used " + floortile_num
            + " Tiles for flooring, these costed " + ((int)Math.ceil(a+b))+ "TL." + "\n" );
            sum = sum + ((int)Math.ceil(a+b));
        }
        else {
            out.append("Classroom "+ classroom.getName() + " used " + wallnum +"m2 of " +  classroom.getWalltype() + " for walls and used " + floortile_num
                    + " Tiles for flooring, these costed " + ((int)Math.ceil(a+b))+"TL." + "\n");
            sum = sum + ((int)Math.ceil(a+b));
        }
    }
    out.append("Total price is: " + sum +"TL." );
    return out;
    }
    public static void Inputfiller (int j, String[] content, String content2[], Decoration[] decoration, Classroom[] classrooms, int len){
        int k = 0;
        while (j < content.length) {
            String[] alldeccontent = content[j].split("\t");
            if (alldeccontent.length >= 4 && "DECORATION".equals(alldeccontent[0]) && !"Tile".equals(alldeccontent[2])) {
                Decoration dec = new Decoration(alldeccontent[1], alldeccontent[2], Integer.parseInt(alldeccontent[3]));
                dec.setPermeter(1);
                decoration[k] = dec;
                k++;
            }
            else if (alldeccontent.length >= 5 && "DECORATION".equals(alldeccontent[0]) && "Tile".equals(alldeccontent[2])) {
                Decoration dec = new Decoration(alldeccontent[1], alldeccontent[2], Integer.parseInt(alldeccontent[3]));
                dec.setPermeter(Integer.parseInt(alldeccontent[4]));
                decoration[k] = dec;
                k++;
            }
            j++;
        }
        for (int i = 0; i < len; i++) {
            String[] allcontent = content[i].split("\t");

            if (allcontent.length >= 6){
                String[] allcontent2 = content2[i].split("\t");
                if (allcontent2.length >= 3) {
                    String wallDecoration = allcontent2[1];
                    String floorDecoration = allcontent2[2];

                    if ("Rectangle".equals(allcontent[2])) {
                        Classroom classroom = new RectangularClassroom(allcontent[1], Integer.parseInt(allcontent[3]),
                                Integer.parseInt(allcontent[4]), Integer.parseInt(allcontent[5]), wallDecoration, floorDecoration);
                        classrooms[i] = classroom;
                    } else if ("Circle".equals(allcontent[2])) {
                        Classroom classroom = new CircularClassroom(allcontent[1], Integer.parseInt(allcontent[3]),
                                Integer.parseInt(allcontent[4]), Integer.parseInt(allcontent[5]), wallDecoration, floorDecoration);
                        classrooms[i] = classroom;
                    }
                } else {
                    System.out.println("Incomplete decoration data for CLASSROOM " + allcontent[1]);
                }
            }
        }

        for (Classroom classroom : classrooms){
            for (Decoration decor : decoration){
                if (classroom.getWallDecoration().equals(decor.getDecName())){
                    classroom.setWalltype(decor.getDecType());
                }
            }
        }}
}