package main.java.network;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.*;
import main.java.GameManager;
import main.java.moveset.Move;

public class NetgamesActor implements OuvidorProxy {

    private static final long serialVersionUID = 1L;
    protected Proxy proxy;
    protected GameManager manager;
    protected boolean connected = false;

    public NetgamesActor() {
        super();
        this.proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }

    /* pass the ga */
    public void defineManager(GameManager manager) {
        this.manager = manager;
    }

    public String conectar(String servidor, String nome) {
        try {
            proxy.conectar(servidor, nome);
        } catch (JahConectadoException e) {
            return "Voce ja esta conectado";
        } catch (NaoPossivelConectarException e) {
            return "Nao foi possivel conectar";
        } catch (ArquivoMultiplayerException e) {
            return "Voce esqueceu o arquivo de propriedades";
        }
        this.setConnected(true);
        return "Sucesso: conectado a Netgames Server";

    }

    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.setConnected(false);
    }

    public void iniciarPartida() {

        try {
            proxy.iniciarPartida(new Integer(2));
        } catch (NaoConectadoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        manager.setPlayerNumbers(posicao);
        manager.setupGame();

        int indiceAdversario = 1;
        if (posicao.equals(1)) indiceAdversario = 2;
        String adversario = proxy.obterNomeAdversario(indiceAdversario);
    }

    @Override
    public void finalizarPartidaComErro(String message) {


    }

    @Override
    public void receberMensagem(String msg) {
        // TODO Auto-generated method stub
    }

    @Override
    public void receberJogada(Jogada jogada) {
        manager.nextMove((Move) jogada);
    }


    @Override
    public void tratarConexaoPerdida() {
        // TODO Auto-generated method stub

    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        // TODO Auto-generated method stub

    }

    public void enviarJogada(Move move) {
        try {
            proxy.enviaJogada(move);
        } catch (NaoJogandoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void encerrarPartida() {
        try {
            proxy.finalizarPartida();
        } catch (NaoConectadoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NaoJogandoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}

