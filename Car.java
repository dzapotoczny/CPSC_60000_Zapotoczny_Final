import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;

public class Car extends JPanel {
    private String dir;

    private char exitDirection, enterDirection;

    private double posX, posY, velX, velY;
    private double prevVelX, prevVelY;
    private double width = 10;
    private double height = 10;

    private long timeSinceLastMoved;

    public Rectangle2D.Double car;

    public Color carColor = Color.BLUE;

    private Car blockingCar;

    private Boolean carBlocked = false;
    private Boolean stoppedAtLight = false;
    private Boolean exitDirectionSelected = false;
    private Boolean nearJunction = false;

    private TrafficLightController closestController;

    private static double velocity = .00005;

    public Car(double x, double y, double vx, double vy) {
        posX = x;
        posY = y;
        velX = vx;
        velY = vy;
        prevVelX = vx;
        prevVelY = vy;

        if(velX < 0){
            dir = "W";
            //rotAngle = Math.toRadians(270);
        } else if (velX > 0){
            dir = "E";
            //rotAngle = Math.toRadians(90);
        } else if (velY < 0){
            dir = "N";
            //rotAngle = Math.toRadians(0);
        } else if (velY > 0){
            dir = "S";
            //rotAngle = Math.toRadians(180);
        }
        car = new Rectangle2D.Double(posX, posY, width, height);
    }
/*
    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        car = new Rectangle2D.Double(posX, posY, width, height);
        AffineTransform at = AffineTransform.getRotateInstance(rotAngle, posX + width / 2, posY + height / 2);
        g2.setColor(color);
        g2.draw(car);
    }
*/
    public void drive(List<Car> cars, List<TrafficLightController> controllers){
        // other vehicle detection
        for (Car c : cars){
            if (c.equals(this)){
                if (c.carBlocked){
                    if (c.dir.equals("N") && (c.posY - 10 > (blockingCar.posY + blockingCar.height))){
                        velY = prevVelY;
                        c.carBlocked = false;
                        c.blockingCar = null;
                    } else if (c.dir.equals("S") && (c.posY + c.height + 10 < blockingCar.posY)){
                        velY = prevVelY;
                        c.carBlocked = false;
                        c.blockingCar = null;
                    } else if (c.dir.equals("E") && (c.posX + c.width + 10 < blockingCar.posX)){
                        velY = prevVelY;
                        c.carBlocked = false;
                        c.blockingCar = null;
                    } else if (c.dir.equals("W") && (c.posX - 10 > blockingCar.posX + blockingCar.width)){
                        velY = prevVelY;
                        c.carBlocked = false;
                        c.blockingCar = null;
                    }
                }
            } else{
                if (velX < 0 && (((posX - 10) < c.posX + c.width) && ((posY + height / 2) > c.posY) && ((posY + height / 2) < (c.posY + c.height)))){
                    velX = 0;
                    carBlocked = true;
                    blockingCar = c;
                    timeSinceLastMoved = System.currentTimeMillis();
                } else if (velX > 0 && (((posX + width + 10) > c.posX) && ((posY + height / 2) > c.posY) && ((posY + height / 2) < (c.posY + c.height)))){
                    velX = 0;
                    carBlocked = true;
                    blockingCar = c;
                    timeSinceLastMoved = System.currentTimeMillis();
                } else if (velY < 0 && (((posY - 10) < c.posY + c.height) && ((posX + width / 2) > c.posX) && ((posX + width / 2) < (c.posX + c.width)))){
                    velY = 0;
                    carBlocked = true;
                    blockingCar = c;
                    timeSinceLastMoved = System.currentTimeMillis();
                } else if (velY > 0 && (((posY + height + 10) > c.posY) && ((posX + width / 2) > c.posX) && ((posX + width / 2) < (c.posX + c.width)))){
                    velY = 0;
                    carBlocked = true;
                    blockingCar = c;
                    timeSinceLastMoved = System.currentTimeMillis();
                }
            }
        }
        if (System.currentTimeMillis() - timeSinceLastMoved > 10000){
            //System.out.println("Car stuck");
            if (dir.equals("N") || dir.equals("S")){
                velY = prevVelY;
            } else if (dir.equals("E") || dir.equals("W")){
                velX = prevVelX;
            }

        }

        // decision making at intersection

        Random r = new Random();

        for (TrafficLightController controller : controllers){
            String exits = "";
            int[] NESW = new int[4];
            int tempExit = controller.junction.junctionExitDirections;


            for (int i = 0; i < 4; i++){
                NESW[i] = tempExit % 10;
                tempExit /= 10;
            }
            int W = NESW[0];
            int S = NESW[1];
            int E = NESW[2];
            int N = NESW[3];



            if (velX <= 0 && (posX - 15 < (controller.junction.laneX + controller.junction.laneWidth)) && (posX > controller.junction.laneX)
                    && (posY > controller.junction.laneY) && (posY + height < (controller.junction.laneY + controller.junction.laneHeight)) && (controller.lightW != null) && !exitDirectionSelected){
                closestController = controller;
                nearJunction = true;
                enterDirection = 'E';
                if (!closestController.lightW.green){
                    velX = 0;
                    stoppedAtLight = true;
                } else if (closestController.lightW.green){
                    velX = prevVelX;
                    stoppedAtLight = false;
                    if (W == 1){
                        exits = exits + "W";
                    }
                    if (S == 1){
                        exits = exits + "S";
                    }
                    if (N == 1){
                        exits = exits + "N";
                    }
                    //System.out.println(exits);
                    exitDirection = exits.charAt(r.nextInt(exits.length()));
                    //System.out.println(exitDirection);
                    exitDirectionSelected = true;
                }

            } else if (velX >= 0 && (posX + width + 15 > (controller.junction.laneX)) && (posX < controller.junction.laneX + controller.junction.laneWidth)
                    && (posY > controller.junction.laneY) && (posY + height < (controller.junction.laneY + controller.junction.laneHeight)) && (controller.lightE != null) && !exitDirectionSelected){
                closestController = controller;
                nearJunction = true;
                enterDirection = 'W';
                if (!closestController.lightE.green){
                    velX = 0;
                    stoppedAtLight = true;
                } else if (closestController.lightE.green){
                    velX = prevVelX;
                    stoppedAtLight = false;
                    if (E == 1){
                        exits = exits + "E";
                    }
                    if (S == 1){
                        exits = exits + "S";
                    }
                    if (N == 1){
                        exits = exits + "N";
                    }
                    //System.out.println(exits);
                    exitDirection = exits.charAt(r.nextInt(exits.length()));
                    //System.out.println(exitDirection);
                    exitDirectionSelected = true;
                }

            } else if (velY <= 0 && (posY - 15 < (controller.junction.laneY + controller.junction.laneHeight)) && (posY > controller.junction.laneY)
                    && (posX > controller.junction.laneX) && (posX + width < (controller.junction.laneX + controller.junction.laneWidth)) && (controller.lightN != null) && !exitDirectionSelected){
                closestController = controller;
                nearJunction = true;
                enterDirection = 'S';
                if (!closestController.lightN.green){
                    velY = 0;
                    stoppedAtLight = true;
                } else if (closestController.lightN.green){
                    velY = prevVelY;
                    stoppedAtLight = false;
                    if (E == 1){
                        exits = exits + "E";
                    }
                    if (W == 1){
                        exits = exits + "W";
                    }
                    if (N == 1){
                        exits = exits + "N";
                    }
                    //System.out.println(exits);
                    exitDirection = exits.charAt(r.nextInt(exits.length()));
                    //System.out.println(exitDirection);
                    exitDirectionSelected = true;
                }
            } else if (velY >= 0 && (posY + height + 15 > (controller.junction.laneY)) && (posY < controller.junction.laneY + controller.junction.laneHeight)
                    && (posX > controller.junction.laneX) && (posX + width < (controller.junction.laneX + controller.junction.laneWidth)) && (controller.lightS != null) && !exitDirectionSelected){
                closestController = controller;
                nearJunction = true;
                enterDirection = 'N';
                //System.out.println(controller.junction.junctionExitDirections);
                //System.out.println(N + " " + E + " " + S + " " + W);
                if (!closestController.lightS.green){
                    velY = 0;
                    stoppedAtLight = true;
                } else if (closestController.lightS.green){
                    velY = prevVelY;
                    stoppedAtLight = false;
                    if (E == 1){
                        exits = exits + "E";
                    }
                    if (W == 1){
                        exits = exits + "W";
                    }
                    if (S == 1){
                        exits = exits + "S";
                    }
                    //System.out.println(controller.junction.laneX + " " + controller.junction.laneY);
                    //System.out.println(exits);
                    exitDirection = exits.charAt(r.nextInt(exits.length()));
                    //System.out.println(exitDirection);
                    exitDirectionSelected = true;

                }
            }
            if (exitDirectionSelected){
                if (velX > 0 && (posX > (closestController.junction.laneX + closestController.junction.laneWidth))){
                    exitDirectionSelected = false;
                    closestController = null;
                    //System.out.println("Direction Selected = false");

                } else if (velX < 0 && ((posX + width) < (closestController.junction.laneX ))){
                    exitDirectionSelected = false;
                    closestController = null;
                    //System.out.println("Direction Selected = false");
                } else if (velY > 0 && (posY > (closestController.junction.laneY + closestController.junction.laneHeight ))){
                    exitDirectionSelected = false;
                    closestController = null;
                    //System.out.println("Direction Selected = false");
                } else if (velY < 0 && ((posY + height) < (closestController.junction.laneY))) {
                    exitDirectionSelected = false;
                    closestController = null;
                    //System.out.println("Direction Selected = false");
                }
            }

            if (exitDirectionSelected){
                if (exitDirection == 'N'){
                    if (enterDirection == 'E'){
                        if (posX <= (closestController.junction.laneX + closestController.junction.laneWidth - 15)){
                            posX = closestController.junction.laneX + closestController.junction.laneWidth - 15;
                            velX = 0;
                            velY = -velocity;
                            prevVelY = velY;
                            prevVelX = velX;

                            enterDirection = ' ';
                            exitDirection = ' ';

                        }
                    } else if (enterDirection == 'W'){
                        if (posX >= (closestController.junction.laneX + closestController.junction.laneWidth - 15)){
                            posX = closestController.junction.laneX + closestController.junction.laneWidth - 15;
                            velX = 0;
                            velY = -velocity;
                            prevVelY = velY;
                            prevVelX = velX;
                            //exitDirectionSelected = false;
                            enterDirection = ' ';
                            exitDirection = ' ';
                        }
                    } else if (enterDirection == 'S'){
                        posX = closestController.junction.laneX + closestController.junction.laneWidth - 15;
                        velX = 0;
                        velY = -velocity;
                        prevVelY = velY;
                        prevVelX = velX;
                        //exitDirectionSelected = false;
                        enterDirection = ' ';
                        exitDirection = ' ';
                    }
                } else if (exitDirection == 'E'){
                    if (enterDirection == 'N'){
                        if (posY >= (closestController.junction.laneY + closestController.junction.laneHeight - 15)){
                            posY = closestController.junction.laneY + closestController.junction.laneHeight - 15;
                            velX = velocity;
                            velY = 0;
                            prevVelY = velY;
                            prevVelX = velX;
                            //exitDirectionSelected = false;
                            enterDirection = ' ';
                            exitDirection = ' ';
                        }
                    } else if (enterDirection == 'S'){
                        if (posY <= (closestController.junction.laneY + closestController.junction.laneHeight - 15)){
                            posY = closestController.junction.laneY + closestController.junction.laneHeight - 15;
                            velX = velocity;
                            velY = 0;
                            prevVelY = velY;
                            prevVelX = velX;
                            //exitDirectionSelected = false;
                            enterDirection = ' ';
                            exitDirection = ' ';
                        }
                    } else if (enterDirection == 'W'){
                        posY = closestController.junction.laneY + closestController.junction.laneHeight - 15;
                        velX = velocity;
                        velY = 0;
                        prevVelY = velY;
                        prevVelX = velX;
                        //exitDirectionSelected = false;
                        enterDirection = ' ';
                        exitDirection = ' ';

                    }
                } else if (exitDirection == 'S'){
                    if (enterDirection == 'E'){
                        if (posX <= (closestController.junction.laneX + 5)){
                            posX = closestController.junction.laneX + 5;
                            velX = 0;
                            velY = velocity;
                            prevVelY = velY;
                            prevVelX = velX;
                            //exitDirectionSelected = false;
                            enterDirection = ' ';
                            exitDirection = ' ';
                        }
                    } else if (enterDirection == 'W'){
                        if (posX >= (closestController.junction.laneX + 5)){
                            posX = closestController.junction.laneX + 5;
                            velX = 0;
                            velY = velocity;
                            prevVelY = velY;
                            prevVelX = velX;
                            //exitDirectionSelected = false;
                            enterDirection = ' ';
                            exitDirection = ' ';
                        }
                    } else if (enterDirection == 'N'){
                        posX = closestController.junction.laneX + 5;
                        velX = 0;
                        velY = velocity;
                        prevVelY = velY;
                        prevVelX = velX;
                        //exitDirectionSelected = false;
                        enterDirection = ' ';
                        exitDirection = ' ';

                    }

                } else if (exitDirection == 'W'){
                    if (enterDirection == 'N'){
                        if (posY >= (closestController.junction.laneY + 5)){
                            posY = closestController.junction.laneY + 5;
                            velX = -velocity;
                            velY = 0;
                            prevVelY = velY;
                            prevVelX = velX;
                            //exitDirectionSelected = false;
                            enterDirection = ' ';
                            exitDirection = ' ';
                        }
                    } else if (enterDirection == 'S'){
                        if (posY <= (closestController.junction.laneY + 5)){
                            posY = closestController.junction.laneY + 5;
                            velX = -velocity;
                            velY = 0;
                            prevVelY = velY;
                            prevVelX = velX;
                            //exitDirectionSelected = false;
                            enterDirection = ' ';
                            exitDirection = ' ';
                        }
                    } else if (enterDirection == 'E'){
                        posY = closestController.junction.laneY + 5;
                        velX = -velocity;
                        velY = 0;
                        prevVelY = velY;
                        prevVelX = velX;
                        //exitDirectionSelected = false;
                        enterDirection = ' ';
                        exitDirection = ' ';
                    }
                }
            }
        }
        if (velX != 0 || velY != 0){
            posX += velX;
            posY += velY;
        } else{

            timeSinceLastMoved = System.currentTimeMillis();
        }

        car = new Rectangle2D.Double(posX, posY, width, height);

    }
}
