# Undergraduate Java Project | Gestion d'un magazin
_**Nicolas Martinet, Valentin Réault, Mohamed Larinouna**_


## Première modélisation

![Première modélisation](temporaryModel1.png)

Le code utilse se trouve dans `src/main/java`.
Composé de :
- [La class App.java](src/main/java/App.java) `src/main/java/App.java`
- [Le dossier Gestionnary](src/main/java/Gestionnary) `src/main/java/Gestionnary`
- [Le dossier Item](src/main/java/Item) `src/main/java/Item`

Celle-ci ne prends pas encore en compte la gestion des rayons et de differents magazins
Aussi la notion d'utilisateur (vendeur, responsable) n'est pas encore programmé

### Ajouter un article

```
Items.add("album", new String[]{"Dark Side of the Moon", "1988", "Pink Floyd", "Universal"});
```

### Afficher les article

```
Items.display();
```

### Ajouter aux stock des article crées

Ici on propose d'ajouter 10 articles :

```
Stock.add("album", "Dark Side of the Moon","1988", 10)
```

### Mettre en vente un article qui se trouve dans les stocks

Ici on propose de vendre `5` albums `Dark Side of the Moon` à `9.99`€

```
Stock.toMarketplace("album","Dark Side of the Moon","1988",5, 9.99);
```

## Prochaine modelisation

![Première modélisation](tamporarymodel2.png)

1. Ajouter la notion d'utilisateur et de panier
2. à determiner
