package com.politecnicomalaga.box2dexample.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.box2dexample.GDXBox2DExample;

/**
 * Created by julienvillegas on 17/01/2017.
 */

/*

Screen es un interface muy potente de Libgdx. Nos permite no tener que hacer transiciones "a mano"
Con ella, todas las veces que la implementemos, estamos creando una "parte" o "pantalla" de nuestro
videojuego, pudiendo activar o desactivar la screen a demanda.

*/
public class TitleScreen implements Screen {

    //Stage: Es otra clase de alto nivel. Representa un "escenario".
    //En un escenario se suelen crear y añadir personajes
    private Stage stage;
    private Game game;   //Referencia al juego "contenedor"


    //Constructor
    public TitleScreen(Game aGame) {
        game = aGame;

        //Para crear el escenario, le damos un ScreenViewport, es decir, un
        //objeto que sabrá pintar ese escenario mediante una cámara que observa
        //lo que pasa en ese escenario
        stage = new Stage(new ScreenViewport());

        //Label es un objeto "Etiqueta de texto", y forma parte de la escena
        //Por lo que se puede decir que es un "actor". Estático, pero actor en escena al fin y al cabo
        Label title = new Label("Box2D Basic demo", GDXBox2DExample.skin,"big-black");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());

        //Añadimos la etiqueta a la pantalla.
        stage.addActor(title);

        //Ahora le toca al botón. Es un botón con texto
        TextButton playButton = new TextButton("Start!",GDXBox2DExample.skin);
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/2-playButton.getHeight()/2);

        //Le añadimos al botón una acción con un listener. MVC
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Cuando el botón se suelte...
                //Le decimos al juego que coja y asigne una screen nueva, en concreto
                //una GameScreen
                game.setScreen(new GameScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //Cuando el botón se pulsa no hacermos nada. Pero hay que implementarlo
                return true;
            }
        });
        //El botón también es un actor.
        stage.addActor(playButton);


         //En una escena, cuando suceden eventos, la "stage" le pasa el evento al "actor" que
         // tiene que atenderlo, siempre que tenga un Listener para entrada (clics de ratón, gamepad o teclado)
         //Es decir: si añadimos cosas a la stage con sus listener, actuarán cuando las cliquemos

    }

    @Override
    public void show() {
        //Cuando se muestra la screen, debemos de decirle a nuestra stage que
        //se haga cargo de la entrada
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        //delta es el delta-time. El tiempo real en milisec que ha pasado entre render y render

        //Limpiamos la screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Se le dice a la stage (escenario, escena) que los actores "actuen"
        //En nuestro caso, los actores son estáticos. No hacen movimientos.
        stage.act();

        //Después, se pintan
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
           //Si nos hace resize del juego con la pantalla inicial, se ejecuta lo que pongamos aquí
           //suele usarse en caso de tener que recalcular tamaños, posiciones, etc.
    }

    @Override
    public void pause() {
         //Qué pasa si nos pausan el juego? en la pantalla principal, no tenemos
        //que hacer nada, por eso está vacío.
    }

    @Override
    public void resume() {
        //Detrás de un pause, siempre al tiempo o se cierra la app o se resume.
        //Esto es para recolocar objetos, recargar partidas guardadas, etc.
    }

    @Override
    public void hide() {
          //A este comportamiento se le llama cuando se oculta la app detrás de otra
          //Por ejemplo, cuando se selecciona en Android otra app.
    }

    @Override
    public void dispose() {
        //No hay nada que explicar. Verdad??
        stage.dispose();
    }
}
