package com.example.PassPlus; /**
 * Created by Али Баба on 24.07.2016.
 */
import java.util.Random;

public class Generator {
    private String PSWD = "";
    public  int id = 0;


    private Generator(){
        id += 1;
    }

    public static Generator init(){

        return new Generator();
    }

    private  void  generate(int from, int to) {
        String pass  = "";
        Random r     = new Random();
        int cntchars = from + r.nextInt(to - from + 1);

        for (int i = 0; i < cntchars; ++i) {
            char next = 0;
            int range = 10;

            switch(r.nextInt(3)) {
                case 0: {next = '0'; range = 10;} break;
                case 1: {next = 'a'; range = 26;} break;
                case 2: {next = 'A'; range = 26;} break;
                }

            pass += (char)((r.nextInt(range)) + next);
        }
        alwaysSave(pass);
        //return pass;
    }

    private void alwaysSave(String pswd){
        this.PSWD = pswd;

    }

    public String getPSWD(Generator gen,int from,int to) {
        gen.generate(from, to);

        return PSWD;
    }
}
