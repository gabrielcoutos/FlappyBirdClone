package com.gabrielcouto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaro;
    private Texture fundo;
    private Integer width;
    private Integer height;
    private float variacao = 0;
    private float gravidade= 0;
    private float startHeight;
    private Texture canoBaixo;
    private Texture canoTopo;
    private float movimentoCano;
    private float space;
    private Random r;
    private int spaceRandom;
    private int estadoJogo =0;// 0 pause  1 começou
    private int score= 0;



    @Override
    public void create() {
        batch = new SpriteBatch();
        passaro = new Texture[3];
        r = new Random();
        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");
        fundo = new Texture("fundo.png");
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        startHeight = height/2;
        movimentoCano = width-105;
        space = 300;

    }

    @Override
    public void render() {
        //movimentacao
        variacao+=Gdx.graphics.getDeltaTime()*5;
        gravidade+=0.5;
        movimentoCano -=Gdx.graphics.getDeltaTime()*200;
        if (variacao > 2)
            variacao = 0;
        if(startHeight>0 || gravidade <0)
            startHeight-=gravidade;
        if(movimentoCano<-canoTopo.getWidth()){
            movimentoCano =width;
            spaceRandom = r.nextInt(canoTopo.getHeight()/2)-(canoTopo.getHeight()/4);
            score++;
        }
        //

        //clique
        if(Gdx.input.justTouched()){
            gravidade=-15;
        }

        //
        batch.begin();
        batch.draw(fundo, 0, 0, width, height);
        batch.draw(canoTopo,movimentoCano,height/2+space/2+spaceRandom);
        batch.draw(canoBaixo,movimentoCano,height/2-canoBaixo.getHeight()-space/2+spaceRandom);
        batch.draw(passaro[(int)variacao], width/4, startHeight);
        batch.end();
    }

    @Override
    public void dispose() {

    }


}
