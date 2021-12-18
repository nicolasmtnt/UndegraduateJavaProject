# Projet Java-Objet | Gestion d'un magazin
_**Réalisé par :**_
_**Nicolas Martinet**_
_**Valentin Réault**_
_**Mohamed Larinouna**_

### Utilisation avec Maven

Le projet a été réalisé avec Maven
La classe qui permet d'executer le projet est [App.java](src/main/java/App.java)

### Utilisation sans Maven (avec [.jar](JavaProject.jar))

Pour lancer le programme, executer avec le terminal la commande ( dans le directory de la racine du projet)
```
java -jar JavaProject.jar
```
ATTENTION : il faut que cette commande soit entré après que le terminal/console soit dans la racine du répertoire du projet (cd *yourCurrentDirectory*/JavaProject)


## Description du fichier

Le code se trouve dans `src/main/java`.
Composé de :
- [La classe App.java](src/main/java/App.java) `src/main/java/App.java`
- [Le dossier Gestionnary](src/main/java/Gestionnary) `src/main/java/Gestionnary`
- [Le dossier Item](src/main/java/Item) `src/main/java/Item`

Celle-ci ne prends pas encore en compte la gestion des rayons et de differents magazins
Aussi la notion d'utilisateur (vendeur, responsable) n'est pas encore programmé

## Réalisatio de test avec Junit

[Dossier de test Junit](src/test/java/com/example) `src/test/java/com/example`

### Ajouter un article

```
Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
Items.add("movie", new String[]{"Star Wars","2010","George Lucas","Lucasfilm"});
```

### Ajouter aux stock des article crées

Ici on propose d'ajouter 50 articles :

```
Stock.add("album", "Dark Side of the Moon","1988", 50)
```

### Afficher les article

```
Items.display();
Stock.display();
```


### Mettre en vente un article qui se trouve dans les stocks

Ici mets en vente `5` albums `Dark Side of the Moon` à `9.99`€
```
Stock.toMarketplace("albums", "Dark Side of the Moon", "2010", 10, 9.99);
```

Ici on ajoute `2` albums `Dark Side of the Moon` au meme prix (Comportement du programme non encore défini si le prix est différent
```
Stock.toMarketplace("albums", "Dark Side of the Moon", "2010", 2, 9.99);
```

![Première modélisation](temporaryModel1.png)