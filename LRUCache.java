import java.util.*;
import java.util.stream.*;

public class LRUCache<K,V> extends LinkedHashMap<K, V> {

    private final int capacity;
    
    public LRUCache() {
        this(10);
    }   
    
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        V val = super.get(key);
        return (val == null ? (V)Integer.valueOf(-1) : val);
    }

    private record Command(String instr, Integer key, Integer value) {}

    private Deque<Command> parse(String input) {
        List<String> lines = input.lines().toList();

        // Process the first line
        String commandsStr = lines.get(0).substring(1, lines.get(0).length() - 1).replaceAll("[ \"]", "");
        List<String> commands = Arrays.stream(commandsStr.split(","))
            .collect(Collectors.toList());

        // Process the second line
        String dataStr = lines.get(1).substring(1, lines.get(1).length() - 1).replaceAll(" ", "");
        List<String> data = Arrays.stream(dataStr.split("\\],\\["))
            .map(s-> s.replaceAll("[ \\[\\]]",""))
            .collect(Collectors.toList());
        
        Deque<Command> deque = IntStream.range(0, commands.size())
            .mapToObj(i -> {
                Command c = null;
                if ("put".equals(commands.get(i))) {
                    String[] KVpair = data.get(i).split(",");
                    c = new Command(
                            commands.get(i),
                            Integer.valueOf(KVpair[0]),
                            Integer.valueOf(KVpair[1])
                    );
                } else {
                    c = new Command(
                            commands.get(i),
                            Integer.valueOf(data.get(i)),
                            null
                    );
                }
                return c;
            })
            .collect(Collectors.toCollection(ArrayDeque::new));
        return deque;
    }
    
    void main() {
        String input = """
            ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
            [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]""";

        Deque<Command> cmds = parse(input);

        LRUCache<Integer, Integer> cache = new LRUCache<>(cmds.pop().key());

        String expectedOutput = "[null, null, null, 1, null, -1, null, -1, 3, 4]";

        List<String> results = new ArrayList<>();
        results.add("null"); 
        while(!cmds.isEmpty()) {
            Command cmd = cmds.pop();
            if ("put".equals(cmd.instr())) {
                cache.put(cmd.key(), cmd.value());
                results.add(null);
            } else if ("get".equals(cmd.instr())) {
                results.add(String.valueOf(cache.get(cmd.key())));
            } else { results.add("null"); }
        }
        String result = results.stream().collect(Collectors.joining(", ", "[", "]"));

        IO.println(result);
        IO.println(expectedOutput.equals(result));
    }
}
