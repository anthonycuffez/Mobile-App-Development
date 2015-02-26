package be.howest.nmct;

/**
 * Created by Anthony on 13/02/2015.
 */
public class BMIInfo {
    //CONSTRUCTOR
    public BMIInfo(float height, int mass){
        this.height = height;
        this.mass = mass;
    }

    //PROPERTIES:
    // HEIGHT:
    private float height;
    public float getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }

    // MASS:
    private int mass;
    public int getMass(){
        return mass;
    }
    public void setMass(int mass){
        this.mass = mass;
    }

    // BMI INDEX:
    private float bmiIndex;
    public float getBmiIndex(){
        bmiIndex = mass / (height*height);
        return bmiIndex;
    }
    public void setBmiIndex(float bmiIndex){
        this.bmiIndex = bmiIndex;
    }

    // CATEGORY:
    private Category category = Category.NORMAL;
    public Category getCategory(float index){
        return category.getCategory(index);
    }
    public static enum Category {
        LARGE_UNDERWEIGHT (0 , 15),
        MEDIUM_UNDERWEIGHT (15, 16),
        UNDERWEIGHT (16, 18.5f),
        NORMAL (18.5f, 25),
        OVERWEIGHT (25, 30),
        MODERATE_OVERWEIGHT (30, 35),
        MEDIUM_OVERWEIGHT (35, 40),
        LARGE_OVERWEIGHT (40, 299);

        private float lowerBoundary;
        private float upperBoundary;

        Category(float lowerBoundary,float upperBoundary){
            this.lowerBoundary = lowerBoundary;
            this.upperBoundary = upperBoundary;
        }

        public boolean isInBoundary(float index){
            if(index > this.lowerBoundary && index < this.upperBoundary){
                return true;
            }
            return false;
        }

        public static Category getCategory(float index){
            for(Category category : Category.values()){
                if(category.isInBoundary(index)) return category;
            }
            return null;
        }
    }
}