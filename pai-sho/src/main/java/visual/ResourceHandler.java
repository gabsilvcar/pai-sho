package main.java.visual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ResourceHandler {
    private static final String resources_path = "/main/resources/";
    /**
     * Retorna um ícone
     * @param resource O recurso a ser carregado
     *
     * @return A imagem pronta para ser utilizada
     */
    public static ImageIcon getImageIcon(ProjectResources resource) {
        return new ImageIcon(getResource(resource.value));
    }

    /**
     * Retorna a imagem bufferizada
     * @param resource O recurso a ser carregado
     *
     * @throws IOException Caso o caminho seja inválido
     *
     * @return A imagem pronta para ser utilizada
     */
    public static BufferedImage getBufferedImage(ProjectResources resource) throws IOException {
        return ImageIO.read(getResource(resource.value));
    }

    private static URL getResource(String resource_name){
        return ResourceHandler.class.getClass().getResource(resources_path + resource_name);
    }


}
