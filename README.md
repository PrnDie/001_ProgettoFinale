# 001_ProgettoFinale

Specifiche del progetto
• Il data-set: https://www.dati.gov.it/api/3/action/package_show?id=96404f82-975e-490e-89e5-966181f72b4c.
• All'interno del progetto sono presenti 2 cartelle: UML, contenente i diagrammi uml semplificati che permottono una rapida analisi delle funzioni del programma; JAR, contiene la libreria JAR del json.simple utilizzato per il parsing degli oggetti dopo la lettura dal CSV.
• Sono presenti commenti in javadoc.
• Il testing non è stato totale, perchè testare tutte le possibili eccezioni e controllare qualasisi tipo di inserimento risoltava molto dispendioso in termini di tempo, quindi non garantiamo la gestione delle eccezioni al 100%.

All’avvio si verifica a presenza di un'eventuale csv obsoleto, viene eliminato e si effettua il download del data-set (csv) che contiene dati in formati CSV partendo dall’indirizzo fornito dopo decodifica del JSON che contiene la URL utile per scaricare il file, al download completato si effettua il parsing dei dati creando delle strutture dati opportune sulla base delle classi.

Su richiesta mediante API GET con rotte distinte:
- /media -->Conteggio elementi unici (per ogni elemento unico indica il numero di occorrenze);
- /metadati --> restituire i metadati (formato JSON) ovvero elenco degli attributi e del tipo
- dati --> restituire statistiche sui dati (formato JSON)
- /dati?filter=<_100 --> Restituire i metadati (formato JSON) ovvero elenco degli attributi e del tipo utilizzare la sintassi seguente '<' o '>' seguito da '_' e il valore da prendere in considerazione
- /dati?filter=$and_<_100_>1000 --> restituire i metadati (formato JSON) ovvero elenco degli attributi e del tipo utilizzare la sintassi deve contenere "$and/$or"_"<
