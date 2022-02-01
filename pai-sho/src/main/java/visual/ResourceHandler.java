package main.java.visual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ResourceHandler {
    public static final String resources_path = "/main/resources/";
    /**
     * Retorna um ícone
     * @param resource O recurso a ser carregado
     *
     * @return A imagem pronta para ser utilizada
     */
    public static ImageIcon getImageIcon(ProjectResources resource) throws IOException {
        return new ImageIcon(ImageIO.read(getResource(resource.value)));
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

    private static InputStream getResource(String resource_name){
        InputStream input = ResourceHandler.class.getResourceAsStream(resources_path + resource_name);
        if (input == null) {
            input = ResourceHandler.class.getClassLoader().getResourceAsStream(resource_name);
        }

        return input;
    }

    /**
     * Retorna a imagem bufferizada
     * @param name O nome da imagem a ser carregada
     *
     * @throws IOException Caso o caminho seja inválido
     *
     * @return A imagem pronta para ser utilizada
     */
    public static BufferedImage getBufferedImage(String name) throws IOException {
        return ImageIO.read(getResource(name));
    }

}
