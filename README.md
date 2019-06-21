# 001_ProgettoFinale

Specifiche del progetto
• Il data-set: https://www.dati.gov.it/api/3/action/package_show?id=96404f82-975e-490e-89e5-966181f72b4c

All’avvio si verifica a presenza di un'eventuale csv obsoleto, viene eliminato e si effettua il download del data-set (csv) che contiene dati in formati CSV partendo dall’indirizzo fornito dopo decodifica del JSON che contiene la URL utile per scaricare il file, al download completato si effettua il parsing dei dati creando delle strutture dati opportune sulla base delle classi.

Su richiesta mediante API GET con rotte distinte:
 /media ------------------------> Conteggio elementi unici (per ogni elemento unico indica il numero di occorrenze);
 /metadati ---------------------> restituire i metadati (formato JSON) ovvero elenco degli attributi e del tipo
 /dati -------------------------> restituire statistiche sui dati (formato JSON)
 /dati?filter=<_100 ------------> restituire i metadati (formato JSON) ovvero elenco degli attributi e del tipo utilizzare la sintassi                                    seguente '<' o '>' seguito da '_' e il valore da prendere in considerazione
 /dati?filter=$and_<_100_>1000 -> restituire i metadati (formato JSON) ovvero elenco degli attributi e del tipo utilizzare la sintassi                                    deve contenere "$and/$or"_"<
