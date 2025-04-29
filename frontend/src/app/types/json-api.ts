export interface JsonApiData<T> {
  type: string;
  attributes: T;
}

export interface JsonApiRequest<T> {
  data: JsonApiData<T>;
}

export interface JsonApiResponse<T> {
  data: JsonApiData<T>;
}
