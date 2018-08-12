// Generated by Dagger (https://google.github.io/dagger).
package search.com.ctest.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import search.com.ctest.network.NetworkLayer;

public final class MovieSearchApplicationModule_ProvideNetworkLayerFactory
    implements Factory<NetworkLayer> {
  private final MovieSearchApplicationModule module;

  public MovieSearchApplicationModule_ProvideNetworkLayerFactory(
      MovieSearchApplicationModule module) {
    this.module = module;
  }

  @Override
  public NetworkLayer get() {
    return Preconditions.checkNotNull(
        module.provideNetworkLayer(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static MovieSearchApplicationModule_ProvideNetworkLayerFactory create(
      MovieSearchApplicationModule module) {
    return new MovieSearchApplicationModule_ProvideNetworkLayerFactory(module);
  }

  public static NetworkLayer proxyProvideNetworkLayer(MovieSearchApplicationModule instance) {
    return Preconditions.checkNotNull(
        instance.provideNetworkLayer(), "Cannot return null from a non-@Nullable @Provides method");
  }
}