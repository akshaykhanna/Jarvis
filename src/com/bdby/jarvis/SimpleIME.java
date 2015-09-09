package com.bdby.jarvis;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class SimpleIME extends InputMethodService implements OnKeyboardActionListener
{
 
private KeyboardView kv;
private Keyboard keyboard;
 
private boolean caps = false;


@Override
public void onPress(int primaryCode) {
}

@Override
public void onRelease(int primaryCode) {            
}

@Override
public void onText(CharSequence text) {     
}

@Override
public void swipeDown() {   
}

@Override
public void swipeLeft() {
}

@Override
public void swipeRight() {
}

@Override
public void swipeUp() {
}
@Override
public View onCreateInputView()
{
    kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
    keyboard = new Keyboard(this, R.xml.qwerty);
    kv.setKeyboard(keyboard);
    kv.setOnKeyboardActionListener(this);
    return kv;
}

 private void playClick(int keyCode)
 {
   //This method plays a sound when a key is pressed. We use the AudioManager class to play the sounds
	
	AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
    switch(keyCode)
    {
    case 32: 
        am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
        break;
    case Keyboard.KEYCODE_DONE:
    case 10: 
        am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
        break;
    case Keyboard.KEYCODE_DELETE:
        am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
        break;              
    default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
    }       
  }
 
 @Override
 public void onKey(int primaryCode, int[] keyCodes) 
 {        
     //this method is used so that our keyboard app can communicate with input fields (usually EditText views) of other applications
	 
	 InputConnection ic = getCurrentInputConnection();//is used to get a connection to the input field of another application
     playClick(primaryCode);
     switch(primaryCode){
     case Keyboard.KEYCODE_DELETE :
         ic.deleteSurroundingText(1, 0);
         //Above Method to delete one or more characters of the input field
         break;
     case Keyboard.KEYCODE_SHIFT:
         caps = !caps;
         keyboard.setShifted(caps);
         kv.invalidateAllKeys();
         //method is used to redraw all keys when state change like caps is pressed
         break;
     case Keyboard.KEYCODE_DONE:
         ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
         // Above  to send events, like KEYCODE_ENTER, to the external application
         break;
     default:
         char code = (char)primaryCode;
         if(Character.isLetter(code) && caps){
             code = Character.toUpperCase(code);
         }
         ic.commitText(String.valueOf(code),1);
         //to add one or more characters to the input field
     }
 }
 
}
