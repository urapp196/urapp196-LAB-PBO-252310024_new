import javax.swing.JOptionPane;

public class tayooo {
    public static void main(String[] args) {
        String nama;
        int confirm;

        do {
            nama = JOptionPane.showInputDialog(
                null, 
                "Input nama buku anda:",
                "Input nama buku anda",
                JOptionPane.QUESTION_MESSAGE
            );

            JOptionPane.showMessageDialog(
                null, 
                "Buku yang diinput: " + nama, 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE
            );

            confirm = JOptionPane.showConfirmDialog(
                null,
                "Apakah ingin menginput buku lagi?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
            );
            
        } while (confirm == JOptionPane.YES_OPTION);
    }
}