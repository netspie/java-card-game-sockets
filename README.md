# Prototyp gry karcianej

### Temat
Prototyp gry karcianej, w której dwaj gracz rozgrywają mecz przy użyciu wcześniej predefiniowanych i wylosowanych kart, z których każda zawiera nazwę oraz kilka statystyk (Atak, Obrona, Szybkość).

### Autor
Dariusz Luśnia

### Wymagania
Aby system działał należy uruchomić serwer, a następnie conajmniej dwóch klientów.

### Przypadki użycia

##### Zarządzanie Kartami (panel Administratora)
- Zaloguj się jako administrator
- Dodaj, Edytuj, Usuń Kartę do puli kart do wylosowania w rozgrywce - dane karty będą zapisane do pliku na dysku na serwerze i odczytywane przy późniejszym rozpoczęciu rozgrywki 

##### Rozgrywka w czasie rzeczywistym
- Zaloguj się jako gracz - połącz się z serwerem z poziomu klienta i utwórz grę jeśli nie istnieje, jeśli istnieje dołącz, maksymalna liczba osób w grze wynosi dwa 
- Rozpocznij grę
- Wylosuj n kart z predefiniowanych kart dla każdego z graczy (automatycznie przez system)
- Na podstawie sumy statystyk "Szybkość" - wylosuj gracza rozpoczynającego grę (system)
- Zagraj WYBRANĄ kartą przeciwko LOSOWEJ karcie drugiego gracza (gracz.. i system)
- Na podstawie statystyk "Atak" i "Obrona" zagranej karty oraz karty przeciwnika wyeliminuj odpowiednią z nich z pola gry
- Ogłoś wygraną jeśli któryś z graczy straci wszystkie karty..

Każda akcji będzie towarzyszyła odpowiednia notyfikacja tekstowa oraz/lub zmiana wyglądu kart na ekranie dla każdego z klientów..

### Implementacja

System składa się z dwóch aplikacji - serwera i klienta. 

##### Serwer

Architektura serwera jest oparta o trzy fundamentalne warstwy:
- common - zawierające wszelki kod który może być użyty w każdym miejscu aplikacji, np. dodatkowe funkcje do operacji na kolekcjach
- features - warstwa dzieląca poszczególne funkcjonalności serwera perkpesktywy wysokopoziomowej, tutaj zarządzanie kartami (cards) oraz rozgrywka w czasie rzeczywistym (combat)
- requests - warstwa odpowiedzialna za wykrywanie połączeń oraz przekazywanie wiadomości głębiej do logiki aplikacji

##### Klient

Klient z kolei to aplikacja okienkowa w Swingu, która umożliwia graczowi lub administratorowi interakcję z serwerem.

Składa się z następujących okien:

- HomeFrame – ekran startowy, wybór sposobu logogwania (gracz / administrator)
- AdminFrame – panel administracyjny do zarządzania kartami (dodawanie, edytowanie, usuwanie)
- CombatFrame – interfejs rozgrywki gracza: wybór kart, wizualizacja pojedynków

