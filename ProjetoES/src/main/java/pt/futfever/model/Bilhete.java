package pt.futfever.model;

import java.awt.image.BufferedImage;
import java.util.Date;

public class Bilhete {
    private int id;
    private String codigoBilhete; //BARCODE
    private int quantidade;
    private Date dataCompra;
    private double precoTotal;

//    public static BufferedImage gerarCodigoBarras(String barcodeText) throws Exception {
//        EAN13Writer barcodeWriter = new EAN13Writer();
//        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);
//
//        return MatrixToImageWriter.toBufferedImage(bitMatrix);
//    }

    public void enviarConfirmacao()
    {

    }

    public String getBilheteDigital()
    {
        return codigoBilhete;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public double getPrecoTotal() {
        return precoTotal=quantidade*100;
    }
}