package danieleSanzari.u5w2d5Project.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("La risorsa con id : " + id + " non è stata trovata!");
    }
}
