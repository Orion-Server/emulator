package Orion.Game.Habbo.Factory;

import Orion.Api.Server.Game.Habbo.Data.IHabboMessenger;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriend;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import Orion.Api.Storage.Repository.Habbo.IHabboMessengerRepository;
import Orion.Game.Habbo.Data.Messenger.MessengerCategory;
import Orion.Game.Habbo.Data.Messenger.MessengerFriend;
import Orion.Game.Habbo.Data.Messenger.MessengerFriendRequest;
import Orion.Game.Habbo.Data.Messenger.MessengerFriendsPage;
import Orion.Game.Habbo.Data.HabboMessenger;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.set.hash.THashSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Singleton
public class HabboMessengerFactory {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IHabboMessengerRepository repository;

    public IHabboMessenger create(final int habboId) {
        final IHabboMessenger messenger = new HabboMessenger();

        messenger.setCategories(this.loadMessengerCategories(habboId));
        messenger.setFriends(this.loadMessengerFriends(habboId));
        messenger.setFriendRequests(this.loadMessengerFriendRequests(habboId));

        return messenger;
    }

    private List<IMessengerCategory> loadMessengerCategories(final int habboId) {
        final List<IMessengerCategory> categories = new ArrayList<>();

        this.repository.loadAllMessengerCategories(result -> {
            if(result == null) return;

            categories.add(new MessengerCategory(result));
        }, habboId);

        return categories;
    }

    private Set<IMessengerFriendsPage> loadMessengerFriends(final int habboId) {
        final Set<IMessengerFriendsPage> pages = ConcurrentHashMap.newKeySet();

        this.repository.loadAllMessengerFriends(result -> {
            if(result == null) return;

            final IMessengerFriend friend = new MessengerFriend(result);
            final IMessengerFriendsPage currentPage = pages.stream().filter(page -> page.getFriends().size() < 750).findFirst().orElse(null);

            if(currentPage != null && currentPage.getFriends().size() < 750) {
                currentPage.addFriend(friend);
                return;
            }

            final IMessengerFriendsPage newFriendPage = new MessengerFriendsPage();

            newFriendPage.addFriend(friend);

            pages.add(newFriendPage);
        }, habboId);

        return pages;
    }

    private THashSet<IMessengerFriendRequest> loadMessengerFriendRequests(final int habboId) {
        final THashSet<IMessengerFriendRequest> requests = new THashSet<>();

        this.repository.loadAllMessengerFriendRequests(result -> {
            if(result == null) return;

            final IMessengerFriendRequest request = new MessengerFriendRequest(result);

            requests.add(request);
        }, habboId);

        return requests;
    }
}
