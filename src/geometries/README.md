# Geometry figures

Polygon: is a generic figure with finite number of vertices

```java
// polygon
Polygon p = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
```

Plane: is a two dimensional infinite rectangle on a three dimensional space \

```java
// xy plane
Plane pl = new Plane(new Point(1, 1, 0), new Point(0, 1, 0), new Point(1, 0, 0));
```

Triangle: is a two dimensional figure with three vertices on a three dimensional space

```java
// triangle with two sides equal to 1 and hypotenuse sqrt(2)
Triangle t = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(1, 1, 0));
```

Cylinder: is a three dimensional figure which has two base circles on top and bottom

```java
// cylinder with radius 5 and height 10
Cylinder c = new Cylinder(new Ray(0, 0, 1), 5, 10);
```

Sphere: is a three dimensional figure which can be described by a radius and center point

```java
// sphere with radius 7 and center on the origin
Sphere s = new Sphere(Point.ZERO, 7);
```
