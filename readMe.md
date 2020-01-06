**Mise en place de l'applications**

Builder le projet avec la commande suivante :

`./mvnw clean install`

Ensuite executez :

`java -jar target/demo-0.0.1-SNAPSHOT.jar com.blablacar.demo.DemoApplication`

Allez sur le lien suivant :

`http://localhost:8080/`

Envoyez un fichier .txt avec les instructions correctes et vous verrez la position finale des points.


**Format attendu du fichier**

Le format attendu est le suivant. La première ligne indique la largeur et la hauteur de la pelouse.

`5 5`

Ensuite, les lignes suivantes sont réunies par paquer de deux. Dans ce paquet, la premier ligne indique la positon initiale du point en abscisse puis en ordonnée. Ensuite la direction est indiquée (N pour nord, E pour est, S pour sud et W pour ouest). Voici ci-dessous un exemple :

`1 2 N`

La deuxième ligne de ce paquet indique les instructions de mouvement du point. L indique que la direction doit tourner à gauche, R indique que la direction doit tourner à droite et F indique d'avancer d'une case suivant la direction du point. Exemple ci-dessous : 

`LFLFLFLFF`


Ceci étant présenté, voici un exemple de fichier attendu par l'application :

```
 5 5
 1 2 N
 LFLFLFLFF
 3 3 E
 FFRFFRFRRF