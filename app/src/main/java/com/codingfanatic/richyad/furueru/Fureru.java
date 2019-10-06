/*
Richard Clarke
Furueru
Purpose: Create a TileService and a Tile that will make the phone vibrate and respond to clicks by toggling

1. Import Tile, TileService, Context, Vibrator, VibrationEffect classes
2. onTileAdded >>> Tile state should be inactive when added
3. onClick >>> Tile state should be active when clicked and Phone should vibrate
4. startVibrating >>> Make the phone vibrate
5. stopVibrating >>> Make the phone stop vibrating

line 56: Remove while loop to prevent infinite loop.
 Note how it affects the functionality. It should vibrate for 2 seconds.
 */
package com.codingfanatic.richyad.furueru;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.content.Context;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.util.Log;

public class Fureru extends TileService {

 //1. onTileAdded >>> Tile state should be inactive when added
 @Override
  public void onTileAdded() {
   Log.d("logtag","onTileAdded called");
   //Update state
   getQsTile().setState(Tile.STATE_INACTIVE);

   //Update appearance
   getQsTile().updateTile();
   Log.d("logtag","Tile updated");
  }

 //3. onClick >>> Tile state should toggle when clicked and Phone should vibrate
 @Override
  public void onClick() {
   Log.d("logtag","onClick called");
    if(getQsTile().getState() == Tile.STATE_INACTIVE){
     getQsTile().setState(Tile.STATE_ACTIVE);
     Log.d("logtag","Should be active");
     startVibrating();
    }
    else {
     getQsTile().setState(Tile.STATE_INACTIVE);
     Log.d("logtag", "Should NOT be active");
    stopVibrating();
    }

   getQsTile().updateTile();
  }

 //4. startVibrating >>> Make the phone vibrate
 public void startVibrating(){
   //Vibrate for a second
   Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
   VibrationEffect vibeEffect = VibrationEffect.createOneShot(10000, 255);
   vibe.vibrate(vibeEffect);
 }
  //5. stopVibrating >>> Make the phone stop vibrating
  public void stopVibrating(){
    //Stop vibrating
    Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    vibe.cancel();
  }

 @Override
 public void onTileRemoved() {
  Log.d("logtag","onTileRemoved called");
  getQsTile().setState(Tile.STATE_INACTIVE);

  //Update appearance
  getQsTile().updateTile();
  Log.d("logtag","Tile updated called");
 }
/*


    @Override
    public void onStartListening() {
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
    }
*/

}

