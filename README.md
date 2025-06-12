# Hangman-Spiel

Ein klassisches Galgenmännchen-Spiel implementiert in Java mit MVC-Architektur.

## Beschreibung

Dieses Projekt ist eine Konsolen-basierte Implementation des klassischen Galgenmännchen-Spiels.
Der Spieler muss versuchen, ein zufällig ausgewähltes Wort zu erraten, indem er Buchstaben vorschlägt oder direkt ein Wort eingibt.
Für jeden falschen Buchstaben verliert der Spieler ein Leben.

## Funktionen

- Zufällige Wortauswahl aus einer vordefinierten Liste
- Oder Möglichkeit, ein eigenes Wort einzugeben
- Benutzerfreundliche Konsolenoberfläche
- Anzeige bereits geratener Buchstaben
- Lebensanzeige
- Möglichkeit, einzelne Buchstaben oder das komplette Wort zu raten

## Projektstruktur

    de.bbq.hangman/
    ├── HangmanGame.java          # Hauptklasse mit main-Methode
    ├── controller/
    │   └── HangmanController.java # Spielsteuerung und Logikkoordination
    ├── model/
    │   ├── HangmanModel.java     # Spiellogik und Zustandsverwaltung
    │   └── WordProvider.java     # Bereitstellung der Wörter
    └── view/
        └── HangmanView.java      # Benutzeroberfläche und Ausgabeformatierung


## Installation

1. Stellen Sie sicher, dass Java JDK 23 oder höher installiert ist
2. Klonen Sie das Repository
3. Kompilieren Sie das Projekt mit: javac de/bbq/hangman/*.java
4. Führen Sie das Spiel aus mit: java de.bbq.hangman.HangmanGame


## Spielanleitung

1. Das Spiel fragt, ob Sie sich selber ein Wort ausdenken oder ein zufälliges Wort wählen möchten
   - Geben Sie "1" für ein eigenes Wort oder "2" für ein zufälliges Wort ein
2. Raten Sie einen Buchstaben oder geben Sie das komplette Wort ein
3. Bei jedem falschen Versuch verlieren Sie ein Leben
4. Das Spiel endet, wenn Sie:
- Das Wort erfolgreich erraten haben
- Alle Leben verloren haben

## Technische Details

- Programmiersprache: Java
- Architekturmuster: Model-View-Controller (MVC)
- Entwickelt von: Christos Poulios
- Version: 1.0

## Features

- MVC-Architektur für klare Trennung der Verantwortlichkeiten
- Erweiterbare Wörterliste in WordProvider
- Benutzerfreundliche Fehlermeldungen
- Überprüfung auf bereits geratene Buchstaben

## Lizenz

Dieses Projekt ist unter der MIT-Lizenz lizenziert.

