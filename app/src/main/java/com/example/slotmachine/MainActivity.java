package com.example.slotmachine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView slotResult, payout, bank, betAmount;
    Button pullSlot, bet1, bet5, bet10;
    int winAmount, bankAmount = 500;
    int slot1, slot2, slot3, currentBet = 1;
    ArrayList<Integer> slots;
    ArrayList<ImageView> slotImages;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slotResult = (TextView) findViewById(R.id.slotResult);
        payout = (TextView) findViewById(R.id.payout);
        bank = (TextView) findViewById(R.id.bank);
        betAmount = (TextView) findViewById(R.id.betAmount);

        pullSlot = (Button) findViewById(R.id.pullSlot);
        bet1 = (Button) findViewById(R.id.bet1);
        bet5 = (Button) findViewById(R.id.bet5);
        bet10 = (Button) findViewById(R.id.bet10);

        ImageView slot1Image = (ImageView) findViewById(R.id.slot1Image);
        ImageView slot2Image = (ImageView) findViewById(R.id.slot2Image);
        ImageView slot3Image = (ImageView) findViewById(R.id.slot3Image);

        rand = new Random();
        slots = new ArrayList<Integer>();
        slotImages = new ArrayList<ImageView>();

        slotImages.add(slot1Image);
        slotImages.add(slot2Image);
        slotImages.add(slot3Image);

        bet1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentBet = 1;
                betAmount.setText("Bet Amount: $" + currentBet);
            }
        });

        bet5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentBet = 5;
                betAmount.setText("Bet Amount: $" + currentBet);
            }
        });

        bet10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentBet = 10;
                betAmount.setText("Bet Amount: $" + currentBet);
            }
        });
    }

    public void pullSlot(View v) {
        String msg = "";
        slot1 = rand.nextInt(9)+1;
        slot2 = rand.nextInt(9)+1;
        slot3 = rand.nextInt(9)+1;

        slots.clear();
        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);

        bankAmount -= currentBet;

        if (bankAmount < 0) {
            bankAmount = 0;
            msg = "Out of Money";
            slotResult.setText(msg);
            bank.setText("Bank: " + bankAmount);
            return;
        }

        for (int slotOfSet = 0; slotOfSet < 3; slotOfSet++) {
            String imageName = "slot_" + slots.get(slotOfSet) + ".PNG";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                slotImages.get(slotOfSet).setImageDrawable(d);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (slot1 == slot2 && slot1 == slot3) {
            if (slot1 == 1) {
                winAmount = 10 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 2) {
                winAmount = 20 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 3) {
                winAmount = 10 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 4) {
                winAmount = 500 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 5) {
                winAmount = 10 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 6) {
                winAmount = 250 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 7) {
                winAmount = 10 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 8) {
                winAmount = 10 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
            else if (slot1 == 9) {
                winAmount = 1000 * currentBet;
                msg = "Winner! $" + winAmount;
                bankAmount += winAmount;
            }
        }
        else if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
            if (slot1 == slot2 || slot1 == slot3) {
                if (slot1 == 3) {
                    winAmount = 5 * currentBet;
                    msg = "Winner! $" + winAmount;
                    bankAmount += winAmount;
                }
                else {
                    winAmount = 0;
                    msg = "Better Luck Next Time!";
                }
            }
            else if (slot2 == slot3) {
                if (slot2 == 3) {
                    winAmount = 5 * currentBet;
                    msg = "Winner! $" + winAmount;
                    bankAmount += winAmount;
                }
                else {
                    winAmount = 0;
                    msg = "Better Luck Next Time!";
                }
            }
        }
        else if (slot1 == 3 || slot2 == 3 || slot3 ==3) {
            winAmount = 2 * currentBet;
            msg = "Winner! $" + winAmount;
            bankAmount += winAmount;
        }
        else {
            winAmount = 0;
            msg = "Better Luck Next Time!";
        }

        slotResult.setText(msg);
        payout.setText("Payout: " + winAmount);
        bank.setText("Bank: " + bankAmount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
