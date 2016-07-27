package com.example.PassPlus;

/**
 * Created by Али Баба on 24.07.2016.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Template {
    private final List<Character> source;
    private final int             count;

    private static final Random random = new Random();

    public Template(List<Character> source, int count) {
        this.source = source;
        this.count = count;
    }

    public List<Character> take() {
        List<Character> taken = new ArrayList<Character>(count);
        for (int i = 0; i < count; i++) {
            taken.add(source.get(random.nextInt(source.size())));
        }

        return taken;
    }
}
/*Last, but not the least, example usage:

        PasswordBuilder builder = new PasswordBuilder();
        builder.lowercase(5)
        .uppercase(5)
        .specials(2)
        .digits(2)
        .shuffle();
        // write 100, 14-char shuffled passwords
        for (int i = 0; i < 100; i++) {
        System.out.println(builder.build());
        }
*/