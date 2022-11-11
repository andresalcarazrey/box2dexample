
package com.politecnicomalaga.box2dexample;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.politecnicomalaga.box2dexample.Screens.TitleScreen;

public class GDXBox2DExample extends Game {
	static public Skin skin;
	static public TextureAtlas textureAtlas;

	@Override
	public void create () {

		//Carga la fuente para poner textos en la splash screen
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		//Carga un textureAtlas para guardar la nota musical
		textureAtlas = new TextureAtlas();
		textureAtlas.addRegion("note",new TextureRegion(new Texture("note.png")));

		//Le dice a GDX que utilice una TitleScreen como vista principal. El render se "traspasa"
		//a este objeto TitleScreen asignado al Game

		this.setScreen(new TitleScreen(this));

	}

	@Override
	public void render () {
		//Aquí no programamos nada más que una llamada a que el juego se renderice
		//Aquí se está pidiendo al juego que renderice la Screen que tuviera activa.
		super.render();


	}

	public void dispose () {
		//Disposamos el skin y el textureAtlas
		skin.dispose();
		textureAtlas.dispose();
	}

}
