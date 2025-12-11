# TD2 - Explorateur de Fichiers Virtuel

Projet POO - ESIEA 3A  
Implementation d'un explorateur de fichiers en Java avec une interface en ligne de commande de type Unix.

---

## Table des matieres

1. [Presentation du projet](#presentation-du-projet)
2. [Fonctionnalites](#fonctionnalites)
3. [Architecture du projet](#architecture-du-projet)
4. [Prerequis](#prerequis)
5. [Installation et compilation](#installation-et-compilation)
6. [Execution](#execution)
7. [Guide d'utilisation](#guide-dutilisation)

---

## Presentation du projet

Ce projet est un explorateur de fichiers virtuel developpe en Java.  
Il simule un systeme de fichiers en memoire et permet de naviguer dedans via des commandes Unix/Linux (ls, cd, mkdir, touch).

L'objectif est de mettre en pratique les concepts de la POO :

- Encapsulation et heritage
- Polymorphisme et interfaces
- Design patterns (Command pattern)
- Separation des responsabilites (MVC)

---

## Fonctionnalites

| Commande | Description |
|----------|-------------|
| ls | Affiche le contenu du repertoire courant (nom et taille) |
| cd chemin | Change le repertoire courant |
| mkdir nom | Cree un nouveau dossier |
| touch nom | Cree un nouveau fichier |
| exit | Quitte l'application |

### Details sur cd

- cd . : reste dans le repertoire courant
- cd .. : remonte au repertoire parent
- cd chemin/vers/dossier : navigation relative avec chemins composes

### Gestion des erreurs

L'application gere les cas d'erreur :

- Commandes inconnues
- Arguments manquants
- Repertoires inexistants
- Noms vides

---

## Architecture du projet

Le projet est organise selon le package main.java.com.esiea.pootd2

### Dossier racine

    TD2/
    |-- src/
    |-- out/
    |-- .gitignore
    |-- TD2.iml
    |-- README.md

### Package commands

    commands/
    |-- parsers/
    |   |-- ICommandParser.java
    |   |-- UnixLikeCommandParser.java
    |-- Command.java
    |-- ChangeDirectoryCommand.java
    |-- ErrorCommand.java
    |-- ListCommand.java
    |-- MakeDirectoryCommand.java
    |-- TouchCommand.java

### Package controllers

    controllers/
    |-- IExplorerController.java
    |-- ExplorerController.java

### Package interfaces

    interfaces/
    |-- IUserInterface.java
    |-- AbstractInterface.java
    |-- TextInterface.java
    |-- HttpInterface.java

### Package models

    models/
    |-- Inode.java
    |-- FileInode.java
    |-- FolderInode.java

### Point d'entree

    ExplorerApp.java

---

## Prerequis

- Java JDK 17+ (ou 25)
- Terminal : PowerShell (Windows) ou bash (Linux/macOS)

---

## Installation et compilation

### Sous Windows (PowerShell)

    # Se placer dans le dossier racine du projet

    # Creer le dossier de sortie
    mkdir TD2\out -ErrorAction SilentlyContinue

    # Compiler tous les fichiers .java
    javac -d TD2\out (Get-ChildItem -Recurse -Filter *.java .\TD2\src | ForEach-Object { $_.FullName })

## Execution

### Mode texte (terminal)

    java -cp TD2\out com.esiea.pootd2.ExplorerApp text

### Mode HTTP (interface web)

    java -cp TD2\out com.esiea.pootd2.ExplorerApp http

Puis ouvrir un navigateur à l'adresse :

    http://localhost:8001/

---

## Guide d'utilisation

### ls - Lister le contenu

    > ls
    documents 2
    images 0
    notes.txt 0

### cd - Changer de repertoire

    > cd documents
    > cd ..
    > cd dir1/dir2
    
### mkdir - Creer un dossier

    > mkdir projets

### touch - Creer un fichier

    > touch readme.txt

### exit - Quitter

    > exit

---

## Auteurs

| Nom    | Prenom | Groupe |
|--------|--------|--------|
| BARDET | Romain | ESIEA 3A |

---

TD2 JAVA - ESIEA