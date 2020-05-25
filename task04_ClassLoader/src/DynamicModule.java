public class DynamicModule extends TrueStaticModule {

    @Override
    public String toString() {
        return "Dynamic Module, version 1! " + (counter++);
    }
}
