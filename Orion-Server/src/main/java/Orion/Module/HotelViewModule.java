package Orion.Module;

import Orion.Api.Server.Game.HotelView.IHotelViewManager;
import Orion.Api.Storage.Repository.HotelView.IHotelViewRepository;
import Orion.Game.HotelView.HotelViewManager;
import Orion.Storage.Repository.HotelView.HotelViewRepository;
import com.google.inject.AbstractModule;

public class HotelViewModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IHotelViewManager.class).to(HotelViewManager.class);

        bind(IHotelViewRepository.class).to(HotelViewRepository.class);
    }
}
