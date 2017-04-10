/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicizehub.club.model;

/**
 *
 * @author budsagorn_ss
 */
public class FeedbackStd {
    private int evId;
    private long stdId;
    private int sumQ1;
    private int sumQ2;
    private int sumQ3;
    private int sumQ4;
    private int sumQ5;
    private int sumQ6;
    private int sumQ7;
    private int sumQ8;
    private int sumQ9;
    private int sumQ10;
 

    public FeedbackStd() {
    }

    public FeedbackStd(int evId, long stdId, int sumQ1, int sumQ2, int sumQ3, int sumQ4, 
                        int sumQ5, int sumQ6, int sumQ7, int sumQ8, int sumQ9, int sumQ10) {
        this.evId = evId;
        this.stdId = stdId;
        this.sumQ1 = sumQ1;
        this.sumQ2 = sumQ2;
        this.sumQ3 = sumQ3;
        this.sumQ4 = sumQ4;
        this.sumQ5 = sumQ5;
        this.sumQ6 = sumQ6;
        this.sumQ7 = sumQ7;
        this.sumQ8 = sumQ8;
        this.sumQ9 = sumQ9;
        this.sumQ10 = sumQ10;
                
    }
 
    
    
    public int getEvId() {
        return evId;
    }

    public void setEvId(int evId) {
        this.evId = evId;
    }

    public long getStdId() {
        return stdId;
    }

    public void setStdId(long stdId) {
        this.stdId = stdId;
    }

    public int getSumQ1() {
        return sumQ1;
    }

    public void setSumQ1(int sumQ1) {
        this.sumQ1 = sumQ1;
    }

    public int getSumQ2() {
        return sumQ2;
    }

    public void setSumQ2(int sumQ2) {
        this.sumQ2 = sumQ2;
    }

    public int getSumQ3() {
        return sumQ3;
    }

    public void setSumQ3(int sumQ3) {
        this.sumQ3 = sumQ3;
    }

    public int getSumQ4() {
        return sumQ4;
    }

    public void setSumQ4(int sumQ4) {
        this.sumQ4 = sumQ4;
    }

    public int getSumQ5() {
        return sumQ5;
    }

    public void setSumQ5(int sumQ5) {
        this.sumQ5 = sumQ5;
    }

    public int getSumQ6() {
        return sumQ6;
    }

    public void setSumQ6(int sumQ6) {
        this.sumQ6 = sumQ6;
    }

    public int getSumQ7() {
        return sumQ7;
    }

    public void setSumQ7(int sumQ7) {
        this.sumQ7 = sumQ7;
    }

    public int getSumQ8() {
        return sumQ8;
    }

    public void setSumQ8(int sumQ8) {
        this.sumQ8 = sumQ8;
    }

    public int getSumQ9() {
        return sumQ9;
    }

    public void setSumQ9(int sumQ9) {
        this.sumQ9 = sumQ9;
    }

    public int getSumQ10() {
        return sumQ10;
    }

    public void setSumQ10(int sumQ10) {
        this.sumQ10 = sumQ10;
    }
    
    
}
