package com.gabrielcouto.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.awt.Color;
import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private int widthPassaro;
    private Texture[] passaro;
    private Texture fundo;
    private Integer width;
    private Integer height;
    private BitmapFont font;
    private float variacao = 0;
    private float gravidade = 0;
    private float startHeight;
    private Texture canoBaixo;
    private Texture canoTopo;
    private float movimentoCano;
    private float space;
    private Random r;
    private int spaceRandom;
    private int estadoJogo = 0;// 0 pause  1 começou
    private int score = 0;
    private boolean ponto = false;
    private Circle passarinCirculo;
    private Rectangle canoTopoRec;
    private Rectangle canoBaixoRec;
    //private ShapeRenderer shapeRenderer;


    @Override
    public void create() {
        batch = new SpriteBatch();
        passaro = new Texture[3];
        r = new Random();
        font = new BitmapFont();
        passarinCirculo = new Circle();

        //shapeRenderer = new ShapeRenderer();
        font.setColor(com.badlogic.gdx.graphics.Color.WHITE);
        font.getData().setScale(6);
        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");
        fundo = new Texture("fundo.png");
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        startHeight = height / 2;
        movimentoCano = width;
        widthPassaro = width / 4;
        space = 300;

    }

    @Override
    public void render() {
        variacao += Gdx.graphics.getDeltaTime() * 5;
        if (variacao > 2)
            variacao = 0;
        if (estadoJogo == 0) {
            if (Gdx.input.justTouched()) {
                estadoJogo = 1;
            }
        } else {
            //movimentacao

            gravidade += 0.5;
            movimentoCano -= Gdx.graphics.getDeltaTime() * 200;

            if (startHeight > 0 || gravidade < 0)
                startHeight -= gravidade;
            if (movimentoCano < -canoTopo.getWidth()) {
                ponto=false;
                movimentoCano = width;
                spaceRandom = r.nextInt(canoTopo.getHeight() / 2) - (canoTopo.getHeight() / 4);
            }
            //

            //Pontuacao
            if (widthPassaro >= movimentoCano) {
                if (!ponto) {
                    score++;
                    ponto=true;
                }

            }

            //

            //clique
            if (Gdx.input.justTouched()) {
                gravidade = -15;
            }
        }

        //
        batch.begin();
        batch.draw(fundo, 0, 0, width, height);
        batch.draw(canoTopo, movimentoCano, height / 2 + space / 2 + spaceRandom);
        batch.draw(canoBaixo, movimentoCano, height / 2 - canoBaixo.getHeight() - space / 2 + spaceRandom);
        batch.draw(passaro[(int) variacao], widthPassaro, startHeight);
        font.draw(batch, String.valueOf(score), width / 2, height - height / 10);
        batch.end();


        passarinCirculo.set(widthPassaro+passaro[0].getWidth()/2,startHeight+passaro[0].getHeight()/2,passaro[0].getWidth()/2);
        canoBaixoRec = new Rectangle(movimentoCano,height / 2 - canoBaixo.getHeight() - space / 2 + spaceRandom,canoBaixo.getWidth(),canoBaixo.getHeight());
        canoTopoRec = new Rectangle(movimentoCano,height / 2 + space / 2 + spaceRandom,canoTopo.getWidth(),canoTopo.getHeight());

        // formas
        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(passarinCirculo.x,passarinCirculo.y,passarinCirculo.radius);
        shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
        shapeRenderer.rect(canoBaixoRec.x,canoBaixoRec.y,canoBaixoRec.width,canoBaixoRec.height);
        shapeRenderer.rect(canoTopoRec.x,canoTopoRec.y,canoTopoRec.width,canoTopoRec.height);
        shapeRenderer.end();*/

        // colisao

        if(Intersector.overlaps(passarinCirculo,canoBaixoRec) || Intersector.overlaps(passarinCirculo,canoTopoRec)){

        }
    }

    @Override
    public void dispose() {

    }


}
