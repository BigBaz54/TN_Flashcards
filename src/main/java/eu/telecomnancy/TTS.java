package eu.telecomnancy;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class TTS {
    public static void main(String[] args)
    {

        try {
            /*
            VoiceManager voiceManager = VoiceManager.getInstance();
            System.setProperty("mbrola.base", "src/main/resources/mbrola");
            Voice voice = voiceManager.getVoice("us1");
            voice.allocate();
            voice.speak("Hello World");

             */

            /*
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText("", null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            synthesizer.deallocate();

             */
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
