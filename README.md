# Getting Started

The current project repository is under development

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains three folders, where:

- `src`: the folder to maintain sources
- `test`: the folder to maintain tests
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

### The basic building structures are located in `src/primitives`

> Vector: is a structures based on point \
> Point: is a 3 dimensional coordinate

```java
Point p = new Point(1, 1, 1);

Vector v = new Vector(1, 1, 1); // -> (1, 1, 1)
```

> Utils folder is a helping structure for defining accuracy for a double and some other features

```java
Util.isZero(Math.Pow(0.1, 40)); // output: true
```

### The geometry figures are located in `src/geometries`

> Polygon: is a generic figure with finite number of vertices \
> Plane: is a two dimensional infinite rectangle on a three dimensional space \
> Triangle: is a two dimensional figure with three vertices on a three dimensional space \
> Cylinder: is a three dimensional figure which has two base circles on top and bottom \
> Sphere: is a three dimensional figure which can be described by a radius and center point

```java
// polygon
Polygon p = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));

// xy plane
Plane pl = new Plane(new Point(1, 1, 0), new Point(0, 1, 0), new Point(1, 0, 0));

// triangle with two sides equal to 1 and hypotenuse sqrt(2)
Triangle t = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(1, 1, 0));

// cylinder with radius 5 and height 10
Cylinder c = new Cylinder(new Ray(0, 0, 1), 5, 10);

// sphere with radius 7 and center on the origin
Sphere s = new Sphere(new Point(0, 0, 0), 7);
```

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
