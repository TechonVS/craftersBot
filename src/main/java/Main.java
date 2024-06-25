import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class Main {

    public static void main(String[] args) {
        // Replace "YOUR_BOT_TOKEN" with your actual bot token
        String token = System.getenv("botToken");

        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    final String content = event.getMessage().getContent();
                    if (content.equalsIgnoreCase("!ping")) {
                        event.getMessage().getChannel().block().createMessage("Pong!").block();
                    }
                });

        client.onDisconnect().block();
    }
}