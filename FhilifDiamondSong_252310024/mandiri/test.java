import javax.swing.JOptionPane;

public class test {
    public static void main(String[] args) {
        int jumlah = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukan jumlah array: ", "Input", JOptionPane.QUESTION_MESSAGE));
        int nilai[] = new int[jumlah];
        
        int total = 0; 
        
        for (int i = 0; i < jumlah; i++) {
            nilai[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Masukan angka: ", "Data array ke-" + i, JOptionPane.QUESTION_MESSAGE));
            total += nilai[i];
        }
        
        double rataRata = (double) total / jumlah;
        
        String output = "";
        for (int i = 0; i < jumlah; i++) {
            output = output + "x[" + i + "]=" + nilai[i] + "\n";
        }
        
        output += "\nTotal: " + total;
        output += "\nRata-rata: " + rataRata;
        
        JOptionPane.showMessageDialog(null, output, "Output Array & Rata-rata", JOptionPane.INFORMATION_MESSAGE);
    }
}