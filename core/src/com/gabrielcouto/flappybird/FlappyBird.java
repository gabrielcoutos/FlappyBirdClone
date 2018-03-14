package com.gabrielcouto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaro;
    private Texture fundo;
    private Integer width;
    private Integer height;
    private float variacao = 0;
    private float gravidade= 0;
    private float startHeight;

    @Override
    public void create() {
        batch = new SpriteBatch();
        passaro = new Texture[3];
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");
        fundo = new Texture("fundo.png");
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        startHeight = height/2;

    }

    @Override
    public void render() {
        variacao+=Gdx.graphics.getDeltaTime()*5;
        gravidade++;

        if (variacao > 2)
            variacao = 0;
        if(startHeight>0)
            startHeight-=gravidade;

        batch.begin();
        batch.draw(fundo, 0, 0, width, height);
        batch.draw(passaro[(int)variacao], 0, startHeight);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
