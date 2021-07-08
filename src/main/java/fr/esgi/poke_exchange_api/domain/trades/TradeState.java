package fr.esgi.poke_exchange_api.domain.trades;

public enum TradeState {
    CANCELLED {
        public String toString() {
            return "cancelled";
        }
    },
    PENDING {
        public String toString() {
            return "pending";
        }
    },
    ACCEPTED {
        public String toString() {
            return "accepted";
        }
    }
}
