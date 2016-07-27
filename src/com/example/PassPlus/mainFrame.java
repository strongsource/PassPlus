package com.example.PassPlus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.io.*;

public class mainFrame extends Activity  {
    /**
     * Called when the activity is first created.
     */
    int quan = 8;
    String[] pswd = new String[quan];
    final String LOG_TAG = "myLogs";
    //ListView lvPass = (ListView) findViewById(R.id.lvPass);
    final String FILENAME = "pswd";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        {
            Generator[] list = new Generator[quan];
            for (int i=0; i<quan;i++ ){
                list[i]= Generator.init();
                pswd[i]=(list[i].getPSWD(list[i],quan,quan*2));
            };
        }
// находим список
        ListView lvPass = (ListView) findViewById(R.id.lvPass);
        Button rb = (Button) findViewById(R.id.readB);

        // устанавливаем режим выбора пунктов списка
        lvPass.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pswd);

        // присваиваем адаптер списку
        lvPass.setAdapter(adapter);

        lvPass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ListView lvPass = (ListView) findViewById(R.id.lvPass);// пишем в лог выделенный элемент
                //Log.d(LOG_TAG, "checked: " + pswd[lvPass.getCheckedItemPosition()]);
                 writeFile(pswd[lvPass.getCheckedItemPosition()]);
            }
        });

        rb.setOnClickListener(new AdapterView.OnClickListener(){

            @Override
            public void onClick(View view) {
                ListView lvPass = (ListView) findViewById(R.id.lvPass);
                Log.d(LOG_TAG, "Чтение файла!!! ");
                        //;

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Последний пароль: "+ readFile(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                LinearLayout toastContainer = (LinearLayout) toast.getView();
                ImageView brainImageView = new ImageView(getApplicationContext());
                brainImageView.setImageResource(R.drawable.a61972);
                toastContainer.addView(brainImageView, 0);
                toast.show();
            }
        });




    }

    void writeFile(String s) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write(s);
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан");

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Пароль сохранен! ", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            LinearLayout toastContainer = (LinearLayout) toast.getView();
            ImageView brainImageView = new ImageView(getApplicationContext());
            brainImageView.setImageResource(R.drawable.a61972);
            toastContainer.addView(brainImageView, 0);
            toast.show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readFile() {
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
                return str;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Пароль не был задан!!!";
    }


}
