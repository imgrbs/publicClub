package publicizehub.club.controller;

import java.util.ArrayList;
import javax.swing.JTable;
import publicizehub.club.model.FeedbackModel;
import publicizehub.club.model.FeedbackStd;

/**
 *
 * @author budsagorn_ss
 */
public class FeedbackController {

    FeedbackModel fbm = new FeedbackModel();

    public void getValue(int valueRadio1, int valueRadio2, int valueRadio3, int valueRadio4, int valueRadio5,
            int valueRadio6, int valueRadio7, int valueRadio8, int valueRadio9, int valueRadio10, int evId, long stdId) {

//        double x = 0.2;
//
//        double percentNumber1 = valueRadio1 * x;
//        double percentNumber2 = valueRadio2 * x;
//        double percentNumber3 = valueRadio3 * x;
//        double percentNumber4 = valueRadio4 * x;
//        double percentNumber5 = valueRadio5 * x;
//        double percentNumber6 = valueRadio6 * x;
//        double percentNumber7 = valueRadio7 * x;
//        double percentNumber8 = valueRadio8 * x;
//        double percentNumber9 = valueRadio9 * x;
//        double percentNumber10 = valueRadio10 * x;
//
//        int setSumQ1 = (int) (percentNumber1 + percentNumber2 + percentNumber3 + percentNumber4 + percentNumber5);
//        int setSumQ2 = (int) (percentNumber6 + percentNumber7 + percentNumber8 + percentNumber9 + percentNumber10);

        fbm.insertValue(evId, stdId, valueRadio1, valueRadio2, valueRadio3, valueRadio4, valueRadio5,
                valueRadio6, valueRadio7, valueRadio8, valueRadio9, valueRadio10);
        
        

    }
       //method ให้กดปุ่มแล้วคำนวณส่งค่าไป tb_feedback
       public void AverSum(){
           
          fbm.setSumQ();
     
       }
       
   
    /*
    public void getArrayList(ArrayList<FeedbackStd> myArrList) {
        FeedbackStd[] fb = new FeedbackStd[myArrList.size()];

        int i;
        for (i = 0; i < fb.length; i++) {
            fb[i] = myArrList.get(i);
           
            
        }
    }
     */
       
       
}
