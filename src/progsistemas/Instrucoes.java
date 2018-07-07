package progsistemas;

public class Instrucoes {

    public void abcd() {
    }

    public int add(int dest, int src) {
        return dest + src;
    }

    public void adda() {
    }

    public int addi(int dest, int literal) {
        return dest + literal;
    }

    public void addq() {
    }

    public void addx() {
    }

    public int and(int dest, int src) {
        String d = Integer.toBinaryString(dest);
        String s = Integer.toBinaryString(src);
        String r = "";
        while (s.length() < d.length()) {
            s = "0" + s;
        }
        while (d.length() < s.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (s.charAt(i) == 1 && d.charAt(i) == 1) {
                r = "1" + r;
            } else {
                r = "0" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int andi(int dest, int literal) {
        String d = Integer.toBinaryString(dest);
        String l = Integer.toBinaryString(literal);
        String r = "";
        while (l.length() < d.length()) {
            l = "0" + l;
        }
        while (d.length() < l.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (l.charAt(i) == 1 && d.charAt(i) == 1) {
                r = "1" + r;
            } else {
                r = "0" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public void anditoccr() {
    }

    public void anditosr() {
    }

    public void asl() {
    }

    public void asr() {
    }

    public void bcc() {
    }

    public void bchg() {
    }

    public void bclr() {
    }

    public int bra(int pc, int d) {
        return pc + d;
    }

    public void bset() {
    }

    public void bsr() {
    }

    public void btst() {
    }

    public void chk() {
    }

    public void clr() {
    }

    public int cmp(int dest, int src) {
        if (dest == src) {
            return 0;
        }
        return 1;
    }

    public void cmpa() {
    }

    public int cmpi(int dest, int literal) {
        if (dest == literal) {
            return 0;
        }
        return 1;
    }

    public void cmpm() {
    }

    public void dbcc() {
    }

    public int divs(int dest, int src) {
        return dest / src;
    }

    public int divu(int dest, int src) {
        return dest / src;
    }

    public void eor() {
    }

    public void eori() {
    }

    public void eoritoccr() {
    }

    public void eoritosr() {
    }

    public void exg() {
    }

    public void ext() {
    }

    public void illegal() {
    }

    public int jmp(int dest) {
        return dest;
    }

    public void jsr() {
    }

    public void lea() {
    }

    public void link() {
    }

    public int lsl(int dest, int count) {
        String r = Integer.toBinaryString(dest).substring(count);
        for (int i = 0; i < count; ++i) {
            r = r + "0";
        }
        return Integer.parseInt(r, 2);
    }

    public int lsr(int dest, int count) {
        String r = Integer.toBinaryString(dest);
        r = r.substring(0, r.length() - count);
        for (int i = 0; i < count; ++i) {
            r = "0" + r;
        }
        return Integer.parseInt(r, 2);
    }

    public int move(int src) {
        int dest = src;
        return dest;
    }

    public void movea() {
    }

    public void movetoccr() {
    }

    public void movefromsr() {
    }

    public void movetosr() {
    }

    public void moveusp() {
    }

    public void movem() {
    }

    public void movep() {
    }

    public void moveq() {
    }

    public int muls(int dest, int src) {
        return dest * src;
    }

    public int mulu(int dest, int src) {
        return dest * src;
    }

    public void nbcd() {
    }

    public int neg(int dest) {
        return -dest;
    }

    public void negx() {
    }

    public void nop() {
    }

    public int not(int dest) {
        String d = Integer.toBinaryString(dest);
        String r = "";
        for (int i = 0; i < d.length(); ++i) {
            if (d.charAt(i) == '1'){
                r = r + "0";
            } else {
                r = r + "1";
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int or(int dest, int src) {
        String d = Integer.toBinaryString(dest);
        String s = Integer.toBinaryString(src);
        String r = "";
        while (s.length() < d.length()) {
            s = "0" + s;
        }
        while (d.length() < s.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (s.charAt(i) == 0 && d.charAt(i) == 0) {
                r = "0" + r;
            } else {
                r = "1" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public int ori(int dest, int literal) {
        String d = Integer.toBinaryString(dest);
        String l = Integer.toBinaryString(literal);
        String r = "";
        while (l.length() < d.length()) {
            l = "0" + l;
        }
        while (d.length() < l.length()) {
            d = "0" + d;
        }
        for (int i = 0; i < d.length(); ++i) {
            if (l.charAt(i) == 0 && d.charAt(i) == 0) {
                r = "0" + r;
            } else {
                r = "1" + r;
            }
        }
        return Integer.parseInt(r, 2);
    }

    public void oritoccr() {
    }

    public void oritosr() {
    }

    public void pea() {
    }

    public void reset() {
    }

    public void rol() {
    }

    public void ror() {
    }

    public void roxl() {
    }

    public void roxr() {
    }

    public void rte() {
    }

    public void rtr() {
    }

    public void rts() {
    }

    public void sbcd() {
    }

    public void scc() {
    }

    public void stop() {
    }

    public int sub(int dest, int src) {
        return dest - src;
    }

    public void suba() {
    }

    public int subi(int dest, int literal) {
        return dest - literal;
    }

    public void subq() {
    }

    public void subx() {
    }

    public void swap() {
    }

    public void tas() {
    }

    public void trap() {
    }

    public void trapv() {
    }

    public void tst() {
    }

    public void unlk() {
    }

}
